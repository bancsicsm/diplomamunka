/*
 * Academic License - for use in teaching, academic research, and meeting
 * course requirements at degree granting institutions only.  Not for
 * government, commercial, or other organizational use.
 * File: string1.h
 *
 * MATLAB Coder version            : 5.0
 * C/C++ source code generated on  : 18-Aug-2020 13:52:38
 */

#ifndef STRING1_H
#define STRING1_H

/* Include Files */
#include <stddef.h>
#include <stdlib.h>
#include "rtwtypes.h"
#include "tdoa_as_func_test_types.h"

/* Function Declarations */
extern double string_hex2dec(const char obj_Value_data[], const int
  obj_Value_size[2]);
extern creal_T string_str2double(const char obj_Value_data[], const int
  obj_Value_size[2]);
extern void string_strtok(const char varargin_1_Value_data[], const int
  varargin_1_Value_size[2], char token_Value_data[], int token_Value_size[2],
  char remain_Value_data[], int remain_Value_size[2]);
extern void string_strtrim(const char obj_Value_data[], const int
  obj_Value_size[2], char y_Value_data[], int y_Value_size[2]);

#endif

/*
 * File trailer for string1.h
 *
 * [EOF]
 */
