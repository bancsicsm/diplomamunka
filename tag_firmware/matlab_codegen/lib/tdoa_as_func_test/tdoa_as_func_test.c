/*
 * Academic License - for use in teaching, academic research, and meeting
 * course requirements at degree granting institutions only.  Not for
 * government, commercial, or other organizational use.
 * File: tdoa_as_func_test.c
 *
 * MATLAB Coder version            : 5.0
 * C/C++ source code generated on  : 18-Aug-2020 13:52:38
 */

/* Include Files */
#include "tdoa_as_func_test.h"
#include "find.h"

#include "minOrMax.h"

#include "rt_nonfinite.h"

#include "tdoa_as_func_test_data.h"
#include "tdoa_as_func_test_initialize.h"
#include <math.h>F

#include <log.h>M
#include <timing.h>
#include <decadriver/deca_device_api.h>
#include "rtwtypes.h"


dist_diff tdoa_as_func_test(uint64_t clks0[TIMING_ANCHOR_COUNT+1][TIMING_ANCHOR_COUNT+1],
                            uint64_t clks1[TIMING_ANCHOR_COUNT+1][TIMING_ANCHOR_COUNT+1],
                            short trid,short ancno)
                            {
    //output
    dist_diff outdiff;

    //running variables for 'for' cycles
    short i, j, aref,m;

    //anchor positions
    double x_pos[TIMING_ANCHOR_COUNT] = {0, 3.795, -0.08, 3.795, 0, 0, 0, 0};
    double y_pos[TIMING_ANCHOR_COUNT] = {0, 0, 4.135, 4.135, 0, 0, 0, 0}; //solve dynamic mode
    double z_pos[TIMING_ANCHOR_COUNT] = {1.1, 1.1, 1.1, 1.1, 0, 0, 0, 0};

    short tagid=TIMING_ANCHOR_COUNT; //index of tag in input matrices

    //declaring variable matrices
    double anc_distances[ancno][ancno];
    double anc_dist_diff[ancno][ancno][ancno];
    double dist_diff[ancno][ancno][ancno];

    //variables for slopes calculations
    double slps[ancno][ancno][ancno];
    uint64_t cla1, cla2, clb1, clb2;
    uint64_t clrefi, clrefj, clti, cltj;

    //variables for best reference choosing
    double res_error[ancno][ancno][ancno];
    double diff_error;
    double bestref_data[ancno];
    int bestref = rtNaN;
    boolean_T b_res_error[ancno];

    /* speed of light and time period */
    double cs = 2.99702547E+8;
    double time_unit=DWT_TIME_UNITS;

    if (!isInitialized_tdoa_as_func_test)
    {
        tdoa_as_func_test_initialize();
    }

    /*  Set default parameters */
    outdiff.trid = trid;

    for (i=0;i<TIMING_ANCHOR_NCOMB2;i++)
    {
        outdiff.dd[i]=-32768;
    }

    /* distances between anchors */
    for (i = 0; i < ancno; i++) {
        for (j = 0; j <= i; j++) {
            anc_distances[i][j] = sqrt(((x_pos[i] - x_pos[j]) * (x_pos[i]-x_pos[j]))
                                       + ((y_pos[i] - y_pos[j]) * (y_pos[i]-y_pos[j]))
                                       + ((z_pos[i] - z_pos[j]) * (z_pos[i]-z_pos[j])));
            anc_distances[j][i] = anc_distances[i][j];
        }
           }

    //anchor distance differences
    for (aref = 0; aref < ancno; aref++) {
        for (i = 0; i < ancno; i++) {
            for (j = 0; j < ancno; j++) {
                if (aref == i || aref == j || i==j) {
                    anc_dist_diff[i][j][aref] = 100000;
                } else {
                    anc_dist_diff[i][j][aref] = anc_distances[i][aref] - anc_distances[j][aref];
                }
            }

        }
    }

    //main calculation
    for (aref = 0; aref < ancno; aref++) { //have to calculate with each anchor as reference

        //default values of slopes
        for (i = 0; i < ancno; i++) {
            for (j = 0; j < ancno; j++) {
                slps[i][j][aref] = 1.0;
            }
        }

        //calculating slope variables
        for (i = 1; i < ancno; i++) {
            for (j = 0; j < i; j++) {
                if (aref!=i && aref!=j) {
                    cla1 = clks0[aref][i];
                    cla2 = clks1[aref][i];
                       if (cla1 > cla2) {
                        cla2 = cla2 + pow(2, 40);
                               }
                    clb1 = clks0[aref][j];
                    clb2 = clks1[aref][j];

                    if (clb1 > clb2) {
                        clb2 = clb2 + pow(2, 40);
                       }
                    if (clb2 == clb1) {
                        slps[i][j][aref] = 0;
                        slps[j][i][aref] = 0;
                    }
                    else {
                        slps[i][j][aref] = uint64_to_double((cla2 - cla1) )
                                /uint64_to_double((clb2 - clb1));

                        slps[j][i][aref] = 1.0 / slps[i][j][aref];
                    }
                }
            }
        }

        /* TDOA calculation */
        for (i = 1; i < ancno; i++) {
            for (j = 0; j < i; j++) {
                if ((i != aref) && (j != aref))
                {
                    /* anchor clocks at reference message arrival */
                    clrefi = clks0[aref][i];
                    clrefj = clks0[aref][j];
                    /* anchor clocks at tag message arrival */
                    clti = clks0[tagid][i];
                    cltj = clks0[tagid][j];

                    if (clti < clrefi) {
                        clti = clti + pow(2, 40);
                    }
                    if (cltj < clrefj) {
                        cltj = cltj + pow(2, 40);
                    }

                    //* distance difference calculation      *//*
                    if (slps[i][j][aref] == 0) {
                        dist_diff[i][j][aref] = 0;
                        }
                    else
                        {
                        dist_diff[i][j][aref] = -cs * ((slps[i][j][aref] * uint64_to_double((cltj - clrefj) )* time_unit)
                                                - (uint64_to_double((clti - clrefi) )* time_unit) +
                                                ((anc_distances[j][aref] - anc_distances[i][aref]) / cs));

                        }
                   if (dist_diff[i][j][aref]==0)
                   {
                       dist_diff[i][j][aref]=-32.768;
                   }
                    dist_diff[j][i][aref] = -dist_diff[i][j][aref];
                      }
               }

        }
    }

    m = 0;

    //finding best references
    //calculating result errors
    for (aref = 0; aref < ancno; aref++) {

        for (i = 0; i < ancno; i++) {
            for (j = 0; j < ancno; j++) {
                if (aref==i || aref==j || i==j)
                {
                    res_error[i][j][aref]=100000;
                }
                else {
                    res_error[i][j][aref] = fabs(dist_diff[i][j][aref] - anc_dist_diff[i][j][aref]);
                }

            }

        }

    }

    //finding best ref based on result error
    for (i = 1; i < ancno; i++)
    {
        for (j = 0; j < i; j++)
        {
            for (aref = 0; aref < 6; aref++)
            {
                bestref_data[aref] = res_error[i][j][aref];

            }
            diff_error = minimum(bestref_data);

            for (aref = 0; aref < ancno; aref++)
            {
                b_res_error[aref] = (res_error[i][j][aref] == diff_error);
                if (b_res_error[aref] == true)
                {
                    bestref = aref;
                  //  LOGT(TAG,"i:%d,j:%d,bestref: %d\n",i,j,bestref);
                }
            }
            //picking random reference on error
            while (bestref == rtNaN || bestref == i || bestref == j)
            {
                bestref = rand() % ancno;
            }

            //getting output

            outdiff.dd[m] = (short)(dist_diff[i][j][bestref]*500);

//            LOGT(TAG,"bestref: %d\n",bestref);

            m++;
        }
      }
               return outdiff;
        }
