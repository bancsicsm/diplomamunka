/*
 * Academic License - for use in teaching, academic research, and meeting
 * course requirements at degree granting institutions only.  Not for
 * government, commercial, or other organizational use.
 * File: tdoa_as_func_test.h
 *
 * MATLAB Coder version            : 5.0
 * C/C++ source code generated on  : 18-Aug-2020 13:52:38
 */

#ifndef TDOA_AS_FUNC_TEST_H
#define TDOA_AS_FUNC_TEST_H

/* Include Files */
#include <stddef.h>
#include <stdlib.h>
#include "rtwtypes.h"
#include "tdoa_as_func_test_types.h"
#include "timing.h"

/* Function Declarations */
typedef struct
{
    short trid;
    short dd[TIMING_ANCHOR_NCOMB2];

}dist_diff;
extern dist_diff tdoa_as_func_test(uint64_t clks0[TIMING_ANCHOR_COUNT+1][TIMING_ANCHOR_COUNT+1],
                                   uint64_t clks1[TIMING_ANCHOR_COUNT+1][TIMING_ANCHOR_COUNT+1],
                                   short trid, short ancno);

#endif

/*
 * File trailer for tdoa_as_func_test.h
 *
 * [EOF]
 */
