/*
 * Academic License - for use in teaching, academic research, and meeting
 * course requirements at degree granting institutions only.  Not for
 * government, commercial, or other organizational use.
 * File: minOrMax.c
 *
 * MATLAB Coder version            : 5.0
 * C/C++ source code generated on  : 18-Aug-2020 13:52:38
 */

/* Include Files */
#include "minOrMax.h"
#include "rt_nonfinite.h"
#include "tdoa_as_func_test.h"

/* Function Definitions */

/*
 * Arguments    : const double x[6]
 * Return Type  : double
 */
double minimum(const double x[6])
{
  double ex;
  int idx;
  int k;
  boolean_T exitg1;
  double d;
  if (!rtIsNaN(x[0])) {
    idx = 1;
  } else {
    idx = 0;
    k = 2;
    exitg1 = false;
    while ((!exitg1) && (k < 7)) {
      if (!rtIsNaN(x[k - 1])) {
        idx = k;
        exitg1 = true;
      } else {
        k++;
      }
    }
  }

  if (idx == 0) {
    ex = x[0];
  } else {
    ex = x[idx - 1];
    idx++;
    for (k = idx; k < 7; k++) {
      d = x[k - 1];
      if (ex > d) {
        ex = d;
      }
    }
  }

  return ex;
}

/*
 * File trailer for minOrMax.c
 *
 * [EOF]
 */
