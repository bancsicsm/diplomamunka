/*
 * Academic License - for use in teaching, academic research, and meeting
 * course requirements at degree granting institutions only.  Not for
 * government, commercial, or other organizational use.
 * File: string1.c
 *
 * MATLAB Coder version            : 5.0
 * C/C++ source code generated on  : 18-Aug-2020 13:52:38
 */

/* Include Files */
#include "string1.h"
#include "rt_nonfinite.h"
#include "str2double.h"
#include "tdoa_as_func_test.h"
#include "tdoa_as_func_test_data.h"
#include <stdio.h>
#include <string.h>

/* Function Definitions */

/*
 * Arguments    : const char obj_Value_data[]
 *                const int obj_Value_size[2]
 * Return Type  : double
 */
double string_hex2dec(const char obj_Value_data[], const int obj_Value_size[2])
{
  double y;
  boolean_T b_signed;
  int nbits;
  boolean_T hasPrefix;
  int first;
  boolean_T exitg1;
  int idx;
  int last;
  unsigned long a;
  unsigned long p16;
  char b_c;
  unsigned long sk;
  if (obj_Value_size[1] == 0) {
    y = 0.0;
  } else {
    b_signed = false;
    nbits = 112;
    hasPrefix = false;
    first = 1;
    exitg1 = false;
    while ((!exitg1) && (first < obj_Value_size[1])) {
      idx = (unsigned char)obj_Value_data[first - 1];
      if ((idx == 0) || bv[idx & 127]) {
        first++;
      } else {
        exitg1 = true;
      }
    }

    if ((obj_Value_data[first - 1] == '0') && (first < obj_Value_size[1]) &&
        ((obj_Value_data[first] == 'x') || (obj_Value_data[first] == 'X'))) {
      first += 2;
      hasPrefix = true;
    }

    last = obj_Value_size[1];
    exitg1 = false;
    while ((!exitg1) && (last > first)) {
      idx = (unsigned char)obj_Value_data[last - 1];
      if ((idx == 0) || bv[idx & 127]) {
        last--;
      } else {
        exitg1 = true;
      }
    }

    if (hasPrefix && (first > last)) {
      first -= 2;
    }

    idx = last - 1;
    while ((idx + 1 > first) && (obj_Value_data[idx] >= '0') &&
           (obj_Value_data[idx] <= '9')) {
      idx--;
    }

    if ((obj_Value_data[idx] == 's') || (obj_Value_data[idx] == 'S')) {
      b_signed = true;
      if (last == idx + 2) {
        nbits = (unsigned char)obj_Value_data[last - 1];
      } else if (last == idx + 3) {
        nbits = 10 * ((unsigned char)obj_Value_data[idx + 1] - 48) + (unsigned
          char)obj_Value_data[last - 1];
      } else {
        nbits = 48;
      }

      last = idx;
    } else {
      if ((obj_Value_data[idx] == 'u') || (obj_Value_data[idx] == 'U')) {
        if (last == idx + 2) {
          nbits = (unsigned char)obj_Value_data[last - 1];
        } else if (last == idx + 3) {
          nbits = 10 * ((unsigned char)obj_Value_data[idx + 1] - 48) + (unsigned
            char)obj_Value_data[last - 1];
        } else {
          nbits = 48;
        }

        last = idx;
      }
    }

    a = 0UL;
    p16 = 1UL;
    for (idx = last; idx >= first; idx--) {
      b_c = obj_Value_data[idx - 1];
      if ((b_c >= '0') && (b_c <= '9')) {
        sk = b_c - 48UL;
      } else if ((b_c >= 'A') && (b_c <= 'F')) {
        sk = b_c - 55UL;
      } else if ((b_c >= 'a') && (b_c <= 'f')) {
        sk = b_c - 87UL;
      } else {
        sk = 0UL;
      }

      a += sk * p16;
      p16 <<= 4;
    }

    if (b_signed) {
      if (nbits - 48 == 64) {
        y = (double)a;
      } else if ((((last - first) + 1) << 2 == nbits - 48) &&
                 (obj_Value_data[first - 1] >= '8')) {
        y = (double)a - (double)p16;
      } else {
        y = (double)a;
      }
    } else {
      y = (double)a;
    }
  }

  return y;
}

/*
 * Arguments    : const char obj_Value_data[]
 *                const int obj_Value_size[2]
 * Return Type  : creal_T
 */
creal_T string_str2double(const char obj_Value_data[], const int obj_Value_size
  [2])
{
  creal_T y;
  int ntoread;
  int k;
  boolean_T exitg1;
  int s1_size[2];
  char b_c;
  int idx;
  char s1_data[21];
  boolean_T isimag1;
  boolean_T isfinite1;
  double scanned1;
  boolean_T unusedU0;
  boolean_T success;
  double scanned2;
  boolean_T isfinite2;
  boolean_T foundsign;
  double b_scanned1;
  y.re = rtNaN;
  y.im = 0.0;
  if (obj_Value_size[1] >= 1) {
    ntoread = 0;
    k = 1;
    exitg1 = false;
    while ((!exitg1) && (k <= obj_Value_size[1])) {
      b_c = obj_Value_data[k - 1];
      if (bv[(unsigned char)b_c & 127] || (b_c == '\x00')) {
        k++;
      } else {
        exitg1 = true;
      }
    }

    s1_size[0] = 1;
    s1_size[1] = (signed char)(obj_Value_size[1] + 2);
    idx = (signed char)(obj_Value_size[1] + 2);
    if (0 <= idx - 1) {
      memset(&s1_data[0], 0, idx * sizeof(char));
    }

    idx = 1;
    readfloat(s1_data, s1_size, &idx, obj_Value_data, obj_Value_size, &k,
              obj_Value_size[1], true, &isimag1, &isfinite1, &scanned1,
              &unusedU0, &success);
    if (isfinite1) {
      ntoread = 1;
    }

    if (success && (k <= obj_Value_size[1])) {
      s1_data[idx - 1] = ' ';
      idx++;
      readfloat(s1_data, s1_size, &idx, obj_Value_data, obj_Value_size, &k,
                obj_Value_size[1], true, &unusedU0, &isfinite2, &scanned2,
                &foundsign, &success);
      if (isfinite2) {
        ntoread++;
      }

      if (success && (k > obj_Value_size[1]) && (isimag1 != unusedU0) &&
          foundsign) {
        success = true;
      } else {
        success = false;
      }
    } else {
      scanned2 = 0.0;
    }

    if (success) {
      s1_data[idx - 1] = '\x00';
      if (ntoread == 2) {
        idx = sscanf(&s1_data[0], "%lf %lf", &scanned1, &scanned2);
        if (idx != 2) {
          scanned1 = rtNaN;
          scanned2 = rtNaN;
        }
      } else {
        if (ntoread == 1) {
          idx = sscanf(&s1_data[0], "%lf", &b_scanned1);
          if (idx != 1) {
            b_scanned1 = rtNaN;
          }

          if (isfinite1) {
            scanned1 = b_scanned1;
          } else {
            scanned2 = b_scanned1;
          }
        }
      }

      if (isimag1) {
        y.re = scanned2;
        y.im = scanned1;
      } else {
        y.re = scanned1;
        y.im = scanned2;
      }
    }
  }

  return y;
}

/*
 * Arguments    : const char varargin_1_Value_data[]
 *                const int varargin_1_Value_size[2]
 *                char token_Value_data[]
 *                int token_Value_size[2]
 *                char remain_Value_data[]
 *                int remain_Value_size[2]
 * Return Type  : void
 */
void string_strtok(const char varargin_1_Value_data[], const int
                   varargin_1_Value_size[2], char token_Value_data[], int
                   token_Value_size[2], char remain_Value_data[], int
                   remain_Value_size[2])
{
  int n;
  int k;
  int itoken;
  int i;
  int loop_ub;
  n = varargin_1_Value_size[1];
  k = 0;
  while ((k + 1 <= n) && (varargin_1_Value_data[k] == ' ')) {
    k++;
  }

  itoken = k + 1;
  while ((k + 1 <= n) && (!(varargin_1_Value_data[k] == ' '))) {
    k++;
  }

  if (k + 1 > varargin_1_Value_size[1]) {
    n = 0;
    i = 0;
  } else {
    n = k;
    i = varargin_1_Value_size[1];
  }

  remain_Value_size[0] = 1;
  loop_ub = i - n;
  remain_Value_size[1] = loop_ub;
  for (i = 0; i < loop_ub; i++) {
    remain_Value_data[i] = varargin_1_Value_data[n + i];
  }

  if (itoken > k) {
    n = 0;
    k = 0;
  } else {
    n = itoken - 1;
  }

  token_Value_size[0] = 1;
  loop_ub = k - n;
  token_Value_size[1] = loop_ub;
  for (i = 0; i < loop_ub; i++) {
    token_Value_data[i] = varargin_1_Value_data[n + i];
  }
}

/*
 * Arguments    : const char obj_Value_data[]
 *                const int obj_Value_size[2]
 *                char y_Value_data[]
 *                int y_Value_size[2]
 * Return Type  : void
 */
void string_strtrim(const char obj_Value_data[], const int obj_Value_size[2],
                    char y_Value_data[], int y_Value_size[2])
{
  int b_j1;
  int j2;
  int i;
  b_j1 = 0;
  while ((b_j1 + 1 <= obj_Value_size[1]) && bv[(unsigned char)
         obj_Value_data[b_j1] & 127] && (!(obj_Value_data[b_j1] == '\x00'))) {
    b_j1++;
  }

  j2 = obj_Value_size[1] - 1;
  while ((j2 + 1 > 0) && bv[(unsigned char)obj_Value_data[j2] & 127] &&
         (!(obj_Value_data[j2] == '\x00'))) {
    j2--;
  }

  if (b_j1 + 1 > j2 + 1) {
    b_j1 = 0;
    j2 = -1;
  }

  y_Value_size[0] = 1;
  j2 -= b_j1;
  y_Value_size[1] = j2 + 1;
  for (i = 0; i <= j2; i++) {
    y_Value_data[i] = obj_Value_data[b_j1 + i];
  }
}

/*
 * File trailer for string1.c
 *
 * [EOF]
 */
