/*
 * Academic License - for use in teaching, academic research, and meeting
 * course requirements at degree granting institutions only.  Not for
 * government, commercial, or other organizational use.
 * File: tdoa_as_func_test_initialize.c
 *
 * MATLAB Coder version            : 5.0
 * C/C++ source code generated on  : 18-Aug-2020 13:52:38
 */

/* Include Files */
#include "tdoa_as_func_test_initialize.h"
#include "eml_rand_mt19937ar_stateful.h"
#include "rt_nonfinite.h"
#include "tdoa_as_func_test.h"
#include "tdoa_as_func_test_data.h"

/* Function Definitions */

/*
 * Arguments    : void
 * Return Type  : void
 */
void tdoa_as_func_test_initialize(void)
{
  rt_InitInfAndNaN();
  c = 2.99702547E+8;
  c_eml_rand_mt19937ar_stateful_i();
  isInitialized_tdoa_as_func_test = true;
}

/*
 * File trailer for tdoa_as_func_test_initialize.c
 *
 * [EOF]
 */
