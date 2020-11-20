///*
// * Academic License - for use in teaching, academic research, and meeting
// * course requirements at degree granting institutions only.  Not for
// * government, commercial, or other organizational use.
// * File: kalmanfil_tdoa.c
// *
// * MATLAB Coder version            : 5.0
// * C/C++ source code generated on  : 18-Aug-2020 13:52:38
// */
//
///* Include Files */
//#include "kalmanfil_tdoa.h"
//#include "rt_nonfinite.h"
//#include "tdoa_as_func_test.h"
//#include <math.h>
//
///* Function Declarations */
//static double rt_powd_snf(double u0, double u1);
//
///* Function Definitions */
//
///*
// * Arguments    : double u0
// *                double u1
// * Return Type  : double
// */
//static double rt_powd_snf(double u0, double u1)
//{
//  double y;
//  double d;
//  double d1;
//  if (rtIsNaN(u0) || rtIsNaN(u1)) {
//    y = rtNaN;
//  } else {
//    d = fabs(u0);
//    d1 = fabs(u1);
//    if (rtIsInf(u1)) {
//      if (d == 1.0) {
//        y = 1.0;
//      } else if (d > 1.0) {
//        if (u1 > 0.0) {
//          y = rtInf;
//        } else {
//          y = 0.0;
//        }
//      } else if (u1 > 0.0) {
//        y = 0.0;
//      } else {
//        y = rtInf;
//      }
//    } else if (d1 == 0.0) {
//      y = 1.0;
//    } else if (d1 == 1.0) {
//      if (u1 > 0.0) {
//        y = u0;
//      } else {
//        y = 1.0 / u0;
//      }
//    } else if (u1 == 2.0) {
//      y = u0 * u0;
//    } else if ((u1 == 0.5) && (u0 >= 0.0)) {
//      y = sqrt(u0);
//    } else if ((u0 < 0.0) && (u1 > floor(u1))) {
//      y = rtNaN;
//    } else {
//      y = pow(u0, u1);
//    }
//  }
//
//  return y;
//}
//
///*
// * prediction
// * Arguments    : const double q_prev[3]
// *                const double zk[15]
// *                const double qa[90]
// *                double q[2]
// * Return Type  : void
// */
//void kalmanfil_tdoa(const double q_prev[3], const double zk[15], const
//double qa[90], double q[2])
//{
//
//}
////  double xkm[2];
////  double d;
////  double d1;
////  int jA;
////  int i;
////  double a_tmp;
////  double b_a_tmp;
////  signed char i1;
////  static const signed char Pkm[4] = { 1, 0, 0, 1 };
////
////  double c_a_tmp;
////  int i2;
////  double d_a_tmp;
////  int j;
////  double Hx[30];
////  double smax;
////  int iy;
////  double s;
////  int mmj_tmp;
////  double A[30];
////  double Hxt[30];
////  double hkm_tmp;
////  int b_tmp;
////  double b_hkm_tmp;
////  int jp1j;
////  double hkm[15];
////  signed char ipiv[15];
////  int k;
////  int ix;
////  double b_A[225];
////  static const signed char Rk[225] = { 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
////    0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0,
////    0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0,
////    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
////    0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0,
////    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
////    5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0,
////    0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0,
////    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
////    0, 0, 5 };
////
////  xkm[0] = q_prev[0];
////  xkm[1] = q_prev[1];
////  d = q_prev[0];
////  d1 = q_prev[1];
////  for (jA = 0; jA < 15; jA++) {
////    a_tmp = d - qa[jA];
////    b_a_tmp = d1 - qa[jA + 15];
////    c_a_tmp = d - qa[jA + 45];
////    d_a_tmp = d1 - qa[jA + 60];
////    smax = qa[jA + 30];
////    s = qa[jA + 75];
////    hkm_tmp = sqrt((a_tmp * a_tmp + b_a_tmp * b_a_tmp) + (1.5 - smax) * (1.5 -
////      smax));
////    b_hkm_tmp = c_a_tmp * c_a_tmp + d_a_tmp * d_a_tmp;
////    hkm[jA] = hkm_tmp - sqrt(b_hkm_tmp + (1.5 - s) * (1.5 - s));
////    s = sqrt(b_hkm_tmp + rt_powd_snf(1.5 - smax, 6.0));
////    smax = a_tmp / hkm_tmp - c_a_tmp / s;
////    Hx[jA] = smax;
////    s = b_a_tmp / hkm_tmp - d_a_tmp / s;
////    Hx[jA + 15] = s;
////    iy = jA << 1;
////    Hxt[iy] = smax;
////    Hxt[iy + 1] = s;
////  }
////
////  for (i = 0; i < 2; i++) {
////    i1 = Pkm[i + 2];
////    for (i2 = 0; i2 < 15; i2++) {
////      iy = i2 << 1;
////      A[i + iy] = (double)Pkm[i] * Hxt[iy] + (double)i1 * Hxt[iy + 1];
////    }
////  }
////
////  for (i = 0; i < 15; i++) {
////    d = Hx[i + 15];
////    d1 = Hx[i] + d * 0.0;
////    d += Hx[i] * 0.0;
////    for (i2 = 0; i2 < 15; i2++) {
////      iy = i2 << 1;
////      jA = i + 15 * i2;
////      b_A[jA] = (d1 * Hxt[iy] + d * Hxt[iy + 1]) + (double)Rk[jA];
////    }
////
////    ipiv[i] = (signed char)(i + 1);
////  }
////
////  for (j = 0; j < 14; j++) {
////    mmj_tmp = 13 - j;
////    b_tmp = j << 4;
////    jp1j = b_tmp + 2;
////    iy = 15 - j;
////    jA = 0;
////    ix = b_tmp;
////    smax = fabs(b_A[b_tmp]);
////    for (k = 2; k <= iy; k++) {
////      ix++;
////      s = fabs(b_A[ix]);
////      if (s > smax) {
////        jA = k - 1;
////        smax = s;
////      }
////    }
////
////    if (b_A[b_tmp + jA] != 0.0) {
////      if (jA != 0) {
////        iy = j + jA;
////        ipiv[j] = (signed char)(iy + 1);
////        ix = j;
////        for (k = 0; k < 15; k++) {
////          smax = b_A[ix];
////          b_A[ix] = b_A[iy];
////          b_A[iy] = smax;
////          ix += 15;
////          iy += 15;
////        }
////      }
////
////      i = (b_tmp - j) + 15;
////      for (jA = jp1j; jA <= i; jA++) {
////        b_A[jA - 1] /= b_A[b_tmp];
////      }
////    }
////
////    iy = b_tmp + 15;
////    jA = b_tmp;
////    for (jp1j = 0; jp1j <= mmj_tmp; jp1j++) {
////      smax = b_A[iy];
////      if (b_A[iy] != 0.0) {
////        ix = b_tmp + 1;
////        i = jA + 17;
////        i2 = (jA - j) + 30;
////        for (k = i; k <= i2; k++) {
////          b_A[k - 1] += b_A[ix] * -smax;
////          ix++;
////        }
////      }
////
////      iy += 15;
////      jA += 15;
////    }
////  }
////
////  for (j = 0; j < 15; j++) {
////    jA = (j << 1) - 1;
////    iy = 15 * j;
////    for (k = 0; k < j; k++) {
////      jp1j = k << 1;
////      i = k + iy;
////      if (b_A[i] != 0.0) {
////        A[jA + 1] -= b_A[i] * A[jp1j];
////        A[jA + 2] -= b_A[i] * A[jp1j + 1];
////      }
////    }
////
////    smax = 1.0 / b_A[j + iy];
////    A[jA + 1] *= smax;
////    A[jA + 2] *= smax;
////  }
////
////  for (j = 14; j >= 0; j--) {
////    jA = (j << 1) - 1;
////    iy = 15 * j - 1;
////    i = j + 2;
////    for (k = i; k < 16; k++) {
////      jp1j = (k - 1) << 1;
////      i2 = k + iy;
////      if (b_A[i2] != 0.0) {
////        A[jA + 1] -= b_A[i2] * A[jp1j];
////        A[jA + 2] -= b_A[i2] * A[jp1j + 1];
////      }
////    }
////  }
////
////  for (j = 13; j >= 0; j--) {
////    if (ipiv[j] != j + 1) {
////      iy = j << 1;
////      smax = A[iy];
////      jA = (ipiv[j] - 1) << 1;
////      A[iy] = A[jA];
////      A[jA] = smax;
////      iy++;
////      smax = A[iy];
////      jA++;
////      A[iy] = A[jA];
////      A[jA] = smax;
////    }
////  }
////
////  for (i = 0; i < 15; i++) {
////    hkm[i] = zk[i] - hkm[i];
////  }
////
////  for (i = 0; i < 2; i++) {
////    d = 0.0;
////    for (i2 = 0; i2 < 15; i2++) {
////      d += A[i + (i2 << 1)] * hkm[i2];
////    }
////
////    q[i] = xkm[i] + d;
//  //}
//
//  /*  Pk=Pkm-Kk*Sk*(Kk'); */
////}
//
///*
// * File trailer for kalmanfil_tdoa.c
// *
// * [EOF]
// */
