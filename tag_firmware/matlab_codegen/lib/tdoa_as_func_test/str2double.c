/*
 * Academic License - for use in teaching, academic research, and meeting
 * course requirements at degree granting institutions only.  Not for
 * government, commercial, or other organizational use.
 * File: str2double.c
 *
 * MATLAB Coder version            : 5.0
 * C/C++ source code generated on  : 18-Aug-2020 13:52:38
 */

/* Include Files */
#include "str2double.h"
#include "rt_nonfinite.h"
#include "tdoa_as_func_test.h"
#include "tdoa_as_func_test_data.h"

/* Function Declarations */
static boolean_T copydigits(char s1_data[], int *idx, const char s_data[], int
  *k, int n, boolean_T allowpoint);

/* Function Definitions */

/*
 * Arguments    : char s1_data[]
 *                int *idx
 *                const char s_data[]
 *                int *k
 *                int n
 *                boolean_T allowpoint
 * Return Type  : boolean_T
 */
static boolean_T copydigits(char s1_data[], int *idx, const char s_data[], int
  *k, int n, boolean_T allowpoint)
{
  boolean_T success;
  boolean_T haspoint;
  boolean_T exitg1;
  char b_c;
  success = (*k <= n);
  haspoint = false;
  exitg1 = false;
  while ((!exitg1) && (success && (*k <= n))) {
    b_c = s_data[*k - 1];
    if ((b_c >= '0') && (b_c <= '9')) {
      s1_data[*idx - 1] = b_c;
      (*idx)++;
      (*k)++;
    } else if (b_c == '.') {
      if (allowpoint && (!haspoint)) {
        success = true;
      } else {
        success = false;
      }

      if (success) {
        s1_data[*idx - 1] = '.';
        (*idx)++;
        haspoint = true;
      }

      (*k)++;
    } else if (b_c == ',') {
      (*k)++;
    } else {
      exitg1 = true;
    }
  }

  return success;
}

/*
 * Arguments    : char s1_data[]
 *                const int s1_size[2]
 *                int *idx
 *                const char s_data[]
 *                const int s_size[2]
 *                int *k
 *                int n
 *                boolean_T allowimag
 *                boolean_T *isimag
 *                boolean_T *b_finite
 *                double *nfv
 *                boolean_T *foundsign
 *                boolean_T *success
 * Return Type  : void
 */
void readfloat(char s1_data[], const int s1_size[2], int *idx, const char
               s_data[], const int s_size[2], int *k, int n, boolean_T allowimag,
               boolean_T *isimag, boolean_T *b_finite, double *nfv, boolean_T
               *foundsign, boolean_T *success)
{
  int b_idx;
  boolean_T isneg;
  boolean_T exitg1;
  char c_idx_0;
  boolean_T guard1 = false;
  boolean_T guard2 = false;
  int b_k;
  int exitg3;
  boolean_T unusedU2;
  char c_idx_1;
  char c_idx_2;
  *isimag = false;
  *b_finite = true;
  *nfv = 0.0;
  b_idx = *idx;
  isneg = false;
  *foundsign = false;
  exitg1 = false;
  while ((!exitg1) && (*k <= n)) {
    c_idx_0 = s_data[*k - 1];
    if (c_idx_0 == '-') {
      isneg = !isneg;
      *foundsign = true;
      (*k)++;
    } else if (c_idx_0 == ',') {
      (*k)++;
    } else if (c_idx_0 == '+') {
      *foundsign = true;
      (*k)++;
    } else if (!bv[(unsigned char)c_idx_0 & 127]) {
      exitg1 = true;
    } else {
      (*k)++;
    }
  }

  *success = (*k <= n);
  if ((*success) && isneg) {
    if ((*idx >= 2) && (s1_data[*idx - 2] == '-')) {
      s1_data[*idx - 2] = ' ';
    } else {
      s1_data[*idx - 1] = '-';
      b_idx = *idx + 1;
    }
  }

  *idx = b_idx;
  if (*success) {
    guard1 = false;
    guard2 = false;
    if (*k <= n) {
      c_idx_0 = s_data[*k - 1];
      if (c_idx_0 == 'j') {
        guard2 = true;
      } else if (c_idx_0 == 'i') {
        if (*k >= n - 1) {
          guard2 = true;
        } else {
          b_k = *k;
          c_idx_0 = '\x00';
          while ((b_k <= n) && (s_data[b_k - 1] == ',')) {
            b_k++;
          }

          if (b_k <= n) {
            c_idx_0 = s_data[b_k - 1];
          }

          b_k++;
          c_idx_1 = '\x00';
          while ((b_k <= n) && (s_data[b_k - 1] == ',')) {
            b_k++;
          }

          if (b_k <= n) {
            c_idx_1 = s_data[b_k - 1];
          }

          b_k++;
          c_idx_2 = '\x00';
          while ((b_k <= n) && (s_data[b_k - 1] == ',')) {
            b_k++;
          }

          if (b_k <= n) {
            c_idx_2 = s_data[b_k - 1];
          }

          if ((((c_idx_0 == 'I') || (c_idx_0 == 'i')) && ((c_idx_1 == 'N') ||
                (c_idx_1 == 'n')) && ((c_idx_2 == 'F') || (c_idx_2 == 'f'))) ||
              (((c_idx_0 == 'N') || (c_idx_0 == 'n')) && ((c_idx_1 == 'A') ||
                (c_idx_1 == 'a')) && ((c_idx_2 == 'N') || (c_idx_2 == 'n')))) {
            guard1 = true;
          } else {
            guard2 = true;
          }
        }
      } else {
        guard1 = true;
      }
    } else {
      guard1 = true;
    }

    if (guard2) {
      if (allowimag) {
        *isimag = true;
        (*k)++;
        exitg1 = false;
        while ((!exitg1) && (*k <= n)) {
          c_idx_0 = s_data[*k - 1];
          if (bv[(unsigned char)c_idx_0 & 127] || (c_idx_0 == '\x00') ||
              (c_idx_0 == ',')) {
            (*k)++;
          } else {
            exitg1 = true;
          }
        }

        if ((*k <= n) && (s_data[*k - 1] == '*')) {
          (*k)++;
          readfloat(s1_data, s1_size, idx, s_data, s_size, k, n, false, &isneg,
                    b_finite, nfv, &unusedU2, success);
        } else {
          s1_data[b_idx - 1] = '1';
          *idx = b_idx + 1;
        }
      } else {
        *success = false;
      }
    }

    if (guard1) {
      b_k = *k;
      c_idx_0 = '\x00';
      while ((b_k <= n) && (s_data[b_k - 1] == ',')) {
        b_k++;
      }

      if (b_k <= n) {
        c_idx_0 = s_data[b_k - 1];
      }

      b_k++;
      c_idx_1 = '\x00';
      while ((b_k <= n) && (s_data[b_k - 1] == ',')) {
        b_k++;
      }

      if (b_k <= n) {
        c_idx_1 = s_data[b_k - 1];
      }

      b_k++;
      c_idx_2 = '\x00';
      while ((b_k <= n) && (s_data[b_k - 1] == ',')) {
        b_k++;
      }

      if (b_k <= n) {
        c_idx_2 = s_data[b_k - 1];
      }

      b_k++;
      if (((c_idx_0 == 'I') || (c_idx_0 == 'i')) && ((c_idx_1 == 'N') ||
           (c_idx_1 == 'n')) && ((c_idx_2 == 'F') || (c_idx_2 == 'f'))) {
        *b_finite = false;
        *nfv = rtInf;
      } else if (((c_idx_0 == 'N') || (c_idx_0 == 'n')) && ((c_idx_1 == 'A') ||
                  (c_idx_1 == 'a')) && ((c_idx_2 == 'N') || (c_idx_2 == 'n'))) {
        *b_finite = false;
        *nfv = rtNaN;
      } else {
        *b_finite = true;
        *nfv = 0.0;
        b_k = *k;
      }

      *k = b_k;
      if (*b_finite) {
        *success = copydigits(s1_data, &b_idx, s_data, &b_k, n, true);
        *idx = b_idx;
        *k = b_k;
        if (*success) {
          *success = true;
          if (b_k <= n) {
            c_idx_0 = s_data[b_k - 1];
            if ((c_idx_0 == 'E') || (c_idx_0 == 'e')) {
              s1_data[b_idx - 1] = 'e';
              *idx = b_idx + 1;
              b_k++;
              while ((b_k <= n) && (s_data[b_k - 1] == ',')) {
                b_k++;
              }

              if (b_k <= n) {
                c_idx_0 = s_data[b_k - 1];
                if (c_idx_0 == '-') {
                  s1_data[b_idx] = '-';
                  *idx = b_idx + 2;
                  b_k++;
                } else {
                  if (c_idx_0 == '+') {
                    b_k++;
                  }
                }
              }

              *k = b_k;
              isneg = copydigits(s1_data, idx, s_data, k, n, false);
              if ((!isneg) || (*k <= b_k)) {
                *success = false;
              }
            }
          }
        }
      } else {
        if ((b_idx >= 2) && (s1_data[b_idx - 2] == '-')) {
          *idx = b_idx - 1;
          s1_data[b_idx - 2] = ' ';
          *nfv = -*nfv;
        }
      }

      exitg1 = false;
      while ((!exitg1) && (*k <= n)) {
        if (bv[(unsigned char)s_data[*k - 1] & 127]) {
          (*k)++;
        } else {
          c_idx_0 = s_data[*k - 1];
          if ((c_idx_0 == '\x00') || (c_idx_0 == ',')) {
            (*k)++;
          } else {
            exitg1 = true;
          }
        }
      }

      if ((*k <= n) && (s_data[*k - 1] == '*')) {
        (*k)++;
        while ((*k <= n) && (bv[(unsigned char)s_data[*k - 1] & 127] || (s_data[*
                 k - 1] == '\x00') || (s_data[*k - 1] == ','))) {
          (*k)++;
        }
      }

      if (*k <= n) {
        c_idx_0 = s_data[*k - 1];
        if ((c_idx_0 == 'i') || (c_idx_0 == 'j')) {
          (*k)++;
          *isimag = true;
        }
      }
    }

    do {
      exitg3 = 0;
      if (*k <= n) {
        c_idx_0 = s_data[*k - 1];
        if (bv[(unsigned char)c_idx_0 & 127] || (c_idx_0 == '\x00') || (c_idx_0 ==
             ',')) {
          (*k)++;
        } else {
          exitg3 = 1;
        }
      } else {
        exitg3 = 1;
      }
    } while (exitg3 == 0);
  }
}

/*
 * File trailer for str2double.c
 *
 * [EOF]
 */
