/*
 * Academic License - for use in teaching, academic research, and meeting
 * course requirements at degree granting institutions only.  Not for
 * government, commercial, or other organizational use.
 * File: isequal.c
 *
 * MATLAB Coder version            : 5.0
 * C/C++ source code generated on  : 18-Aug-2020 13:52:38
 */

/* Include Files */
#include "isequal.h"
#include "rt_nonfinite.h"
#include "tdoa_as_func_test.h"

/* Function Definitions */

/*
 * Arguments    : const int varargin_1_Value_size[2]
 * Return Type  : boolean_T
 */
boolean_T isequal(const int varargin_1_Value_size[2])
{
  boolean_T p;
  boolean_T b_p;
  p = false;
  b_p = (varargin_1_Value_size[1] == 0);
  return b_p || p;
}

/*
 * File trailer for isequal.c
 *
 * [EOF]
 */
