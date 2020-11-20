/*
 * Academic License - for use in teaching, academic research, and meeting
 * course requirements at degree granting institutions only.  Not for
 * government, commercial, or other organizational use.
 * File: kalmanfil_tdoa.h
 *
 * MATLAB Coder version            : 5.0
 * C/C++ source code generated on  : 18-Aug-2020 13:52:38
 */

#ifndef KALMANFIL_TDOA_H
#define KALMANFIL_TDOA_H

/* Include Files */
#include <stddef.h>
#include <stdlib.h>
#include "rtwtypes.h"
#include "tdoa_as_func_test_types.h"

/* Function Declarations */
extern void kalmanfil_tdoa(const double q_prev[3], const double zk[15], const
  double qa[90], double q[2]);

#endif

/*
 * File trailer for kalmanfil_tdoa.h
 *
 * [EOF]
 */
