/*
 * Academic License - for use in teaching, academic research, and meeting
 * course requirements at degree granting institutions only.  Not for
 * government, commercial, or other organizational use.
 * File: str2double.h
 *
 * MATLAB Coder version            : 5.0
 * C/C++ source code generated on  : 18-Aug-2020 13:52:38
 */

#ifndef STR2DOUBLE_H
#define STR2DOUBLE_H

/* Include Files */
#include <stddef.h>
#include <stdlib.h>
#include "rtwtypes.h"
#include "tdoa_as_func_test_types.h"

/* Function Declarations */
extern void readfloat(char s1_data[], const int s1_size[2], int *idx, const char
                      s_data[], const int s_size[2], int *k, int n, boolean_T
                      allowimag, boolean_T *isimag, boolean_T *b_finite, double *
                      nfv, boolean_T *foundsign, boolean_T *success);

#endif

/*
 * File trailer for str2double.h
 *
 * [EOF]
 */
