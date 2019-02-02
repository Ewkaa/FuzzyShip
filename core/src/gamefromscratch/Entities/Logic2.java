package gamefromscratch.Entities;

import java.awt.*;

import static java.lang.Math.*;


public class Logic2 {


    private static float nl[][] = new float[6][201];
    private static float ml[][] = new float[6][261];
    private static float fl[][] = new float[3][301];
    private static float nr[][] = new float[1][201];
    private static float mr[][] = new float[2][261];
    private static float fr[][] = new float[3][301];
    private static float na[][] = new float[1][201];
    private static float ma[][] = new float[2][261];
    private static float fa[][] = new float[3][301];
    public static float[][] turnShip = new float[8][91];
    public static float[][] result = new float[8][91];
    public static float[][] agregation = new float[2][91];


    private float counter = 0;
    private float denominator = 0;


    // public static SonarRangeFinder sonarSensor = new ParallaxPing(IntelliBrain.getDigitalIO(3));
    //public static Motor motor = IntelliBrain.getMotor(1);


    public Logic2() {
    }

    public float getOutput(float distance,float distance2, float distance3) {

        int i = (int) distance;
        int j = (int) distance2;
        int k = (int) distance3;
        counter = 0;
        denominator = 0;

        float near_left = nl[1][i];
        float middle_left = ml[2][i];
        float far_left = fl[3][i];
        float near_right = nr[1][j];
        float middle_right = mr[2][j];
        float far_right = fr[3][j];
        float near_ahead = na[1][k];
        float middle_ahead = ma[2][k];
        float far_ahead = fa[3][k];
        //get sensor readings

        //fuzzification


        float R1 = findMinFuzzy(near_left, near_right, near_ahead);
        float R2 = findMinFuzzy(near_left, near_right, middle_ahead);
        float R3 = findMinFuzzy(near_left, near_right, far_ahead);
        float R4 = findMinFuzzy(near_left, middle_right, near_ahead);
        float R5 = findMinFuzzy(near_left, middle_right, middle_ahead);
        float R6 = findMinFuzzy(near_left, middle_right, far_ahead);
        float R7 = findMinFuzzy(near_left, far_right, near_ahead);
        float R8 = findMinFuzzy(near_left, far_right, middle_ahead);
        float R9 = findMinFuzzy(near_left, far_right, far_ahead);
        float R10 = findMinFuzzy(middle_left, near_right, near_ahead);
        float R11 = findMinFuzzy(middle_left, near_right, middle_ahead);
        float R12 = findMinFuzzy(middle_left, near_right, far_ahead);
        float R13 = findMinFuzzy(middle_left, middle_right, near_ahead);
        float R14 = findMinFuzzy(middle_left, middle_right, middle_ahead);
        float R15 = findMinFuzzy(middle_left, middle_right, far_ahead);
        float R16 = findMinFuzzy(middle_left, far_right, near_ahead);
        float R17 = findMinFuzzy(middle_left, far_right, middle_ahead);
        float R18 = findMinFuzzy(middle_left, far_right, far_ahead);
        float R19 = findMinFuzzy(far_left, near_right, near_ahead);
        float R20 = findMinFuzzy(far_left, near_right, middle_ahead);
        float R21 = findMinFuzzy(far_left, near_right, far_ahead);
        float R22 = findMinFuzzy(far_left, middle_right, near_ahead);
        float R23 = findMinFuzzy(far_left, middle_right, middle_ahead);
        float R24 = findMinFuzzy(far_left, middle_right, far_ahead);
        float R25 = findMinFuzzy(far_left, far_right, near_ahead);
        float R26 = findMinFuzzy(far_left, far_right, middle_ahead);
        float R27 = findMinFuzzy(far_left, far_right, far_ahead);
        float strongly_left = findMaxFuzzy(R1, R13, R19, R25);
        float average_left = findMaxFuzzy(R10, R20, R22, R26);
        float slightly_left = findMaxFuzzy(R11, R12, R21, R23);
        float stright = findMaxFuzzy(R2, R3, R14, R15, R18, R24, R27);
        float slightly_right = findMaxFuzzy(R5, R6, R9, R17);
        float average_right = findMaxFuzzy(R4, R8, R16);
        float strongly_right = findMaxFuzzy(R7);


        for (i = 0; i <201; ++i) {
            float x = (float) i;
            result[0][i] = x;
            if (i >= 0 & i <= 15) {
                result[1][i] = findMinFuzzy(strongly_left, turnShip[1][i]);
            }

            if (i >= 5 & i <= 30) {
                result[2][i] = findMinFuzzy(average_left, turnShip[2][i]);
            }

            if (i >= 15 & i <= 45) {
                result[3][i] = findMinFuzzy(slightly_left, turnShip[3][i]);
            }

            if (i >= 30 & i <= 60) {
                result[4][i] = findMinFuzzy(stright, turnShip[4][i]);
            }

            if (i >= 45 & i <= 75) {
                result[5][i] = findMinFuzzy(slightly_right, turnShip[5][i]);
            }

            if (i >= 60 & i <= 85) {
                result[6][i] = findMinFuzzy(average_right, turnShip[6][i]);
            }

            if (i >= 75 & i <= 90) {
                result[7][i] = findMinFuzzy(strongly_right, turnShip[7][i]);
            }
        }

        for (i = 0; i < turnShip[0].length; ++i) {
            agregation[0][i] = result[0][i];
            agregation[1][i] = findMaxFuzzy(result[1][i], result[2][i], result[3][i], result[4][i], result[5][i], result[6][i], result[7][i]);
        }

        for (i = 0; i < turnShip[0].length; ++i) {
            counter += agregation[0][i] * agregation[1][i];
            denominator += agregation[1][i];


        }
        return counter / denominator;
    }



    public static float findMaxFuzzy(float... number) {
        float max = 0.0F;
        float[] var2 = number;
        int var3 = number.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            float f = var2[var4];
            max = Math.max(max, f);
        }

        return max;
    }



    public static float findMinFuzzy(float...number) {
        float min = 1.0F;
        float[] var2 = number;
        int var3 = number.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            float f = var2[var4];
            min = Math.min(min, f);
        }

        return min;
    }


    public static void fuzzy(float x1) {
        float values[] = new float[3];
        float near_y = 0;        //membership
        float medium_y = 0;
        float far_y = 0;

        class Sprite {
            public Boolean Animating;

            public float xPos, yPos;
            public float angle;
            public float xDuration, yDuration;

            public float xDestination, yDestination;


            //pobranie celu
            public void SetDestination(float x, float y) {
                xDestination = x;
                yDestination = y;
            }
            //ruch do celu
            public void PointTowardsDestination() {
                float x = xDestination - xPos;
                float y = yDestination - yPos;

                float lAngle = (float) Math.atan2(y, x);

                lAngle = (float) abs(lAngle * 180 / Math.PI);

                if ((x >= 0) && (y >= 0)) {
                    lAngle = 360 - lAngle;
                }
                if ((x < 0) && (y >= 0)) {
                    lAngle = 270 - (lAngle - 90);
                }

                angle = abs(lAngle);
            }
            public  float createAngleFuzzySet() {
                float x = 0.0F;

                for(int i = 0; i < turnShip[0].length; ++i) {
                    x = (float)i;
                    turnShip[0][i] = x;
                    if (i >= 0 & i <= 5) {
                        turnShip[1][i] = 1.0F;
                    }

                    if (i >= 5 & i <= 15) {
                        turnShip[1][i] = (15.0F - x) / 10.0F;
                    }

                    if (i >= 5 & i <= 15) {
                        turnShip[2][i] = (x - 5.0F) / 10.0F;
                    }

                    if (i >= 15 & i <= 30) {
                        turnShip[2][i] = (30.0F - x) / 15.0F;
                    }

                    if (i >= 15 & i <= 30) {
                        turnShip[3][i] = (x - 15.0F) / 15.0F;
                    }

                    if (i >= 30 & i <= 45) {
                        turnShip[3][i] = (45.0F - x) / 15.0F;
                    }

                    if (i >= 30 & i <= 45) {
                        turnShip[4][i] = (x - 30.0F) / 15.0F;
                    }

                    if (i >= 45 & i <= 60) {
                        turnShip[4][i] = (60.0F - x) / 15.0F;
                    }

                    if (i >= 45 & i <= 60) {
                        turnShip[5][i] = (x - 45.0F) / 15.0F;
                    }

                    if (i >= 60 & i <= 75) {
                        turnShip[5][i] = (75.0F - x) / 15.0F;
                    }

                    if (i >= 60 & i <= 75) {
                        turnShip[6][i] = (x - 60.0F) / 15.0F;
                    }

                    if (i >= 75 & i <= 85) {
                        turnShip[6][i] = (85.0F - x) / 10.0F;
                    }

                    if (i >= 75 & i <= 85) {
                        turnShip[7][i] = (x - 75.0F) / 10.0F;
                    }

                    if (i >= 85 & i <= 91) {
                        turnShip[7][i] = 1.0F;
                    }
                }

                return x;
            }




            public void MoveInDirection(Double pAngle) {
                Double x = cos((pAngle * Math.PI) / 180);
                Double y = sqrt((1 - (x * x)));

                xPos += x;
                if (pAngle <= 180) {
                    yPos -= y;
                } else {
                    yPos += y;
                }

            }}}     }
               /* float a = position.x - xPos;
                float b = sprite.Y - yPos;
                float c, temp;

                c = (a * a) + (b * b);
                c = (float) sqrt(c);

                pAngle = (float) Math.atan2(b, a);
                pAngle = (float) (pAngle * 180 / Math.PI);
                temp = Math.abs(pAngle);
                if ((angle >= 0) && (angle <= 90)) {
                    pAngle = abs(angle + pAngle);
                    if (pAngle > 180) {
                        pAngle = 360 - pAngle;
                    }
                } else if ((angle > 90) && (angle <= 180)) {
                    pAngle = abs(angle + pAngle);
                    if (pAngle > 180) {
                        pAngle = 360 - pAngle;
                    }
                } else if ((angle > 180) && (angle <= 270)) {
                    pAngle = abs(angle + pAngle);
                    if (pAngle > 180) {
                        pAngle = 360 - pAngle;
                    }
                    pAngle = Math.abs(pAngle);
                } else if ((angle > 270) && (angle <= 360)) {
                    pAngle = Math.abs(angle + pAngle);
                    if (pAngle > 180) {
                        pAngle = 360 - pAngle;
                    }
                    pAngle = Math.abs(pAngle);
                }

                if ((a < 0) && (b > 0)) {
                    if ((angle < 180 - temp) || (angle > 180 - temp + 180)) {
                        pAngle *= -1;
                    }
                } else if ((a > 0) && (b > 0)) {
                    if ((angle < 180 - temp) || (angle > 180 - temp + 180)) {
                        pAngle *= -1;
                    }
                } else if ((a > 0) && (b < 0)) {
                    Double x = sprite.X - xPos;
                    Double y = yPos - sprite.Y;
                    temp = (float) Math.atan2(y, x);
                    temp = (float) (temp * 180 / Math.PI);
                    temp = Math.abs(temp);
                    if ((angle < temp + 180) && (angle > temp)) {
                        pAngle *= -1;
                    }
                } else if ((a < 0) && (b < 0)) {
                    Double x = sprite.X - xPos;
                    Double y = yPos - sprite.Y;
                    temp = (float) Math.atan2(y, x);
                    temp = (float) (temp * 180 / Math.PI);
                    temp = Math.abs(temp);
                    if ((angle < temp + 180) && (angle > temp)) {
                        pAngle *= -1;
                    }
                }

                dist = c;
                //if (c < 120) { return true; } else { return false; }
                return false;
            }

        }
        return pAngle;
    }*/
