/******************************************************************************
 *  Compilation:  javac MarkovChain.java
 *  Execution:    java MarkovChain
 ******************************************************************************/
import java.util.*;

public class MarkovChain {

    /**
   * Calculates the square of a double.
   * 
   * @return Returns x*x
   */

  public static float sqr(float x) {
    return x * x;
  }

  /**
   * Returns the average of an array of double.
   */

  public static float mean(float[] v) {
    float tot = (float)0.0;
    for (int i = 0; i < v.length; i++)
      tot += v[i];
    return tot / v.length;
  }

   /**
   * Returns the variance of the array of double.
   */

  public static float variance(float[] v) {
    float mu = (float)mean(v);
    float sumsq = (float)0.0;
    for (int i = 0; i < v.length; i++)
      sumsq += sqr(mu - v[i]);
    return sumsq / (v.length);
  }

   public static void main(String[] args) { 

      // the state transition matrix, it is a irreducible discrete Markov Chain
      double[][] transition = { { 0.2, 0.4, 0.4},
                                { 0.5, 0.1, 0.4},
                                { 0.3, 0.2, 0.5}
                              };

      int N = 3;                        // number of states
      int state = 1;                // current state
      int steps = 0;                    // number of transitions

      int totalSample = 150000;
      int rep = 10000;
      float[] stateIndicator = new float[totalSample];

      // run Markov chain
      for (int s = 0; s < totalSample; s++) {
         steps = 0;
         int stateAsOne = 0;
         for (int r = 0; r < rep; r++) {
            steps++;
            double ran = Math.random();
            double sum = 0.0;

         // determine next state
         for (int j = 0; j < N; j++) {
            sum += transition[state][j];
            if (ran <= sum) {
               state = j;
               if (state == 1) {
                  stateAsOne += 1;
               }
               break;
            }
         }

         }
      
         stateIndicator[s] = ((float)stateAsOne/10000);
         //System.out.println(stateAsOne);
      }

      // for (int index = 0; index < 10; index++){
      //    System.out.println(stateIndicator[index]);
      // }
      System.out.println(variance(stateIndicator));
   }




}