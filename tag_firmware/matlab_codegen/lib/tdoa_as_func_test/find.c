/*
 * Academic License - for use in teaching, academic research, and meeting
 * course requirements at degree granting institutions only.  Not for
 * government, commercial, or other organizational use.
 * File: find.c
 *
 * MATLAB Coder version            : 5.0
 * C/C++ source code generated on  : 18-Aug-2020 13:52:38
 */

/* Include Files */
#include "find.h"
#include "rt_nonfinite.h"
#include "tdoa_as_func_test.h"

/* Function Definitions */

/*
 * Arguments    : const boolean_T x[6]
 *                int i_data[]
 *                int i_size[1]
 * Return Type  : void
 */
void eml_find(const boolean_T x[6], int i_data[], int i_size[1])
{
  int idx;
  int ii;
  boolean_T exitg1;
  idx = 0;
  ii = 0;
  exitg1 = false;
  while ((!exitg1) && (ii < 6)) {
    if (x[ii]) {
      idx++;
      i_data[idx - 1] = ii + 1;
      if (idx >= 6) {
        exitg1 = true;
      } else {
        ii++;
      }
    } else {
      ii++;
    }
  }

  if (1 > idx) {
    i_size[0] = 0;
  } else {
    i_size[0] = idx;
  }
}

/*
 * File trailer for find.c
 *
 * [EOF]
 */
