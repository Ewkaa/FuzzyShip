package gamefromscratch.Entities;

public class Logic {
    public static float[][] left = new float[4][1001];
    public static float[][] right = new float[4][1001];
    public static float[][] ahead = new float[4][1001];
    public static float[][] turnShip = new float[8][91];
    public static float[][] result = new float[8][201];
    public static float[][] agregation = new float[2][91];

    public Logic() {
    }
    //skręt
    public float angleFuzzy() {
        float x = 0.0F;

        for(int i = 0; i < turnShip[0].length; ++i) {
            x = (float)i;
            turnShip[0][i] = x;
            if (i >= 0 && i <= 5) {
                turnShip[1][i] = 1.0F;
            }

            if (i >= 5 && i <= 15) {
                turnShip[1][i] = (15 - x) /(15-5);
            }

            if (i >= 5 & i <= 15) {
                turnShip[2][i] = ((x - 5) / (15-5))*50;
            }

            if (i >= 15 && i <= 30) {
                turnShip[2][i] = (30 - x) /(30-15);
            }

            if (i >= 15 && i <= 30) {
                turnShip[3][i] = (x - 15) / (30-15);
            }

            if (i >= 30 && i <= 45) {
                turnShip[3][i] = (45 - x) / (45-30);
            }

            if (i >= 45 && i <= 60 ) {
                turnShip[4][i] = (x-45 ) / (60-45);
            }
            if (i >=  45 && i <= 60) {
                turnShip[4][i] = (60-x ) / (60-45);
            }

            if (i >= 60 && i <=75 ) {
                turnShip[5][i] = (x - 60) / (75-60);
            }

            if (i >= 60 && i <= 75) {
                turnShip[5][i] = (75 - x) /(75-60);
            }

            if (i >= 60 && i <= 75) {
                turnShip[6][i] = (x - 60.0F) / (75-60);
            }

            if (i >= 75 && i <= 85) {
                turnShip[6][i] = (85 - x) / (85-75);
            }

            if (i >= 85 && i <= 91) {
                turnShip[7][i] = (x - 85) / (91-85);
            }

            if (i >= 230 && i <= 301) {
                turnShip[7][i] = 1.0F;
            }
        }

        return x;
    }
    //odległość
    public void distanceFuzzy(int min, int a, int b, int c, int d, int max) {
        float x ;

        for(int i = 0; i < turnShip[0].length; ++i) {
            x = (float)i;
            left[0][i] = x;
            right[0][i] = x;
            ahead[0][i] = x;
            float l1=0;
            if (i >= min && i <= a)
                l1  = 1.0F;
            else if (i >= a && i <= b)
                l1 = ((b - x) / (b - a))*10;

            left[1][i]=l1;

            float l2=0;
            if (i >= a && i <= b)
                l2 = ((x - a) / (b - a))*10;
            else if (i >= b && i <= c)
                l2= ((c - x) / (c - b))*10;
            left[2][i]=l2;

            float l3=0;
            if (i >= b && i <= d) {
                l3 = (x - (float)b) / (float)(d - b);
            } else if (i >= d && i <= max)
                l3 = 1.0F;
            left[3][i]=l3;


            if (i >= min && i <= a) {
                right[1][i] = 1.0F;
            } else if (i >= a && i <= b) {
                right[1][i] = ((float)b - x) / (float)(b - a);
            }

            if (i >= a && i <= b) {
                right[2][i] = (x - (float)a) / (float)(b - a);
            } else if (i >= b && i <= c) {
                right[2][i] = ((float)c - x) / (float)(c - b);
            }

            if (i >= b && i <= d) {
                right[3][i] = (x - (float)b) / (float)(d - b);
            } else if (i >= d && i <= max) {
                right[3][i] = 1.0F;
            }

            if (i >= min && i <= a) {
                ahead[1][i] = 1.0F;
            } else if (i >= a && i <= b) {
                ahead[1][i] = ((float)b - x) / (float)(b - a);
            }

            if (i >= a && i <= b) {
                ahead[2][i] = (x - (float)a) / (float)(b - a);
            } else if (i >= b && i <= c) {
                left[2][i] = ((float)c - x) / (float)(c - b);
            }

            if (i >= b && i <= d) {
                ahead[3][i] = (x - (float)b) / (float)(d - b);
            } else if (i >= d && i <= max) {
                ahead[3][i] = 1.0F;
            }
        }

    }
    //wartosc max
    public static float maxFuzzy(float... number) {
        float max = 0.0F;
        float[] var2 = number;
        int var3 = number.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            float f = var2[var4];
            max = Math.max(max, f);
        }

        return max;
    }
    //wartość minimalna
    public static float minFuzzy(float... number) {
        float min = 1.0F;
        float[] var2 = number;
        int var3 = number.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            float f = var2[var4];
            min = Math.min(min, f);
        }

        return min;
    }
    //logika+reguły - zwrot kąt
    public static float getLogic(int in_left, int in_right, int in_ahead) {

        float counter = 0.0F;
        float denominator = 0.0F;
        float near_left = left[1][in_left];
        System.out.println(" nl"+ near_left);
        System.out.println("nearleft "+ left[1][in_left]);
        float middle_left = left[2][in_left];
        System.out.println(" ml"+ middle_left);
        System.out.println("middleleft "+ left[2][in_left]);
        float far_left = left[3][in_left];
        System.out.println(" dalek " +far_left);
        float near_right = right[1][in_right];
        float middle_right = right[2][in_right];
        float far_right = right[3][in_right];
        System.out.println(" daleko prawo " +far_right);
        float near_ahead = ahead[1][in_ahead];
        System.out.println(" "+near_ahead);
        float middle_ahead = ahead[2][in_ahead];
        System.out.println(" "+middle_ahead);
        float far_ahead = ahead[3][in_ahead];
        System.out.println(" daleko prosto " +far_ahead);

        float R1 = minFuzzy(near_left, near_right, near_ahead);
        float R2 = minFuzzy(near_left, near_right, middle_ahead);
        float R3 = minFuzzy(near_left, near_right, far_ahead);
        float R4 = minFuzzy(near_left, middle_right, near_ahead);
        float R5 = minFuzzy(near_left, middle_right, middle_ahead);
        float R6 = minFuzzy(near_left, middle_right, far_ahead);
        float R7 = minFuzzy(near_left, far_right, near_ahead);
        float R8 = minFuzzy(near_left, far_right, middle_ahead);
        float R9 = minFuzzy(near_left, far_right, far_ahead);
        float R10 = minFuzzy(middle_left, near_right, near_ahead);
        float R11 = minFuzzy(middle_left, near_right, middle_ahead);
        float R12 = minFuzzy(middle_left, near_right, far_ahead);
        float R13 = minFuzzy(middle_left, middle_right, near_ahead);
        float R14 = minFuzzy(middle_left, middle_right, middle_ahead);
        float R15 = minFuzzy(middle_left, middle_right, far_ahead);
        float R16 = minFuzzy(middle_left, far_right, near_ahead);
        float R17 = minFuzzy(middle_left, far_right, middle_ahead);
        float R18 = minFuzzy(middle_left, far_right, far_ahead);
        float R19 = minFuzzy(far_left, near_right, near_ahead);
        float R20 = minFuzzy(far_left, near_right, middle_ahead);
        float R21 = minFuzzy(far_left, near_right, far_ahead);
        float R22 = minFuzzy(far_left, middle_right, near_ahead);
        float R23 = minFuzzy(far_left, middle_right, middle_ahead);
        float R24 = minFuzzy(far_left, middle_right, far_ahead);
        float R25 = minFuzzy(far_left, far_right, near_ahead);
        float R26 = minFuzzy(far_left, far_right, middle_ahead);
        float R27 = minFuzzy(far_left, far_right, far_ahead);
        float strongly_left = maxFuzzy( R1,R13,R19,R25 );
        float average_left = maxFuzzy( R10, R20, R22, R26);
        float slightly_left = maxFuzzy(R11, R12, R21, R23);
        float stright = maxFuzzy( R2, R3, R14, R15, R18, R24, R27);
        float slightly_right = maxFuzzy(R5, R6, R9, R17);
        float average_right = maxFuzzy(R4, R8, R16);
        float strongly_right = maxFuzzy(R7);


        for (int i = 0; i < turnShip[0].length; i++)
        {
            float x = i;
            result[0][i] = x;
            if (i >= 0 & i <= 15) {
                result[1][i] = minFuzzy(strongly_left, turnShip[1][i]);
            }

            if (i >= 5 & i <= 30) {
                result[2][i] = minFuzzy(average_left, turnShip[2][i]);
            }

            if (i >= 15 & i <= 45) {
                result[3][i] = minFuzzy(slightly_left, turnShip[3][i]);
            }

            if (i >= 30 & i <= 60) {
                result[4][i] = minFuzzy(stright, turnShip[4][i]);
            }

            if (i >= 45 & i <= 75) {
                result[5][i] = minFuzzy(slightly_right, turnShip[5][i]);
            }

            if (i >= 60 & i <= 85) {
                result[6][i] = minFuzzy(average_right, turnShip[6][i]);
            }

            if (i >= 75 & i <= 90) {
                result[7][i] = minFuzzy(strongly_right, turnShip[7][i]);
            }
        }

        for(int i = 0; i < turnShip[0].length; ++i) {
            agregation[0][i] = result[0][i];
            agregation[1][i] = maxFuzzy(result[1][i], result[2][i], result[3][i], result[4][i], result[5][i], result[6][i], result[7][i]);
        }

        for(int i = 0; i < turnShip[0].length; ++i) {
            counter += agregation[0][i] * agregation[1][i];
            denominator += agregation[1][i];
        }

        float y = counter / denominator;
        // System.out.println("kat zmiany "+  (y-45));
        return y -25;

    }
}