/*
 * Academic License - for use in teaching, academic research, and meeting
 * course requirements at degree granting institutions only.  Not for
 * government, commercial, or other organizational use.
 * File: _coder_tdoa_as_func_test_api.h
 *
 * MATLAB Coder version            : 5.0
 * C/C++ source code generated on  : 18-Aug-2020 13:52:38
 */

#ifndef _CODER_TDOA_AS_FUNC_TEST_API_H
#define _CODER_TDOA_AS_FUNC_TEST_API_H

/* Include Files */
#include <stddef.h>
#include <stdlib.h>
#include "tmwtypes.h"
#include "mex.h"
#include "emlrt.h"

/* Variable Declarations */
extern emlrtCTX emlrtRootTLSGlobal;
extern emlrtContext emlrtContextGlobal;

/* Function Declarations */
extern void MEXGlobalSyncInFunction(const emlrtStack *sp);
extern void MEXGlobalSyncOutFunction(boolean_T skipDirtyCheck);
extern void tdoa_as_func_test(real_T datafile, real_T anchor_file, real_T *px,
  real_T *py);
extern void tdoa_as_func_test_api(const mxArray * const prhs[2], int32_T nlhs,
  const mxArray *plhs[2]);
extern void tdoa_as_func_test_atexit(void);
extern void tdoa_as_func_test_initialize(void);
extern void tdoa_as_func_test_terminate(void);
extern void tdoa_as_func_test_xil_shutdown(void);
extern void tdoa_as_func_test_xil_terminate(void);

#endif

/*
 * File trailer for _coder_tdoa_as_func_test_api.h
 *
 * [EOF]
 */
