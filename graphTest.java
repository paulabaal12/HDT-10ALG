import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class GraphTest {
	
	@Test
   private int[][] shortestpath(int[][] adj, int[][] path){
        int n = adj.length;
        int[][] ans = new int[n][n];
        copy(ans, adj);
    	for (int k=0; k<n;k++) 
            for (int i=0; i<n; i++) 
        	for (int j=0; j<n;j++) 
                    if (ans[i][k]+ans[k][j] < ans[i][j]) {
                        ans[i][j] = ans[i][k]+ans[k][j];
          		        path[i][j] = path[k][j];
                    }
    	return ans;
    }
    
    @Test
    private void copy(int[][] emptyMatrix, int[][] matrix){
        for (int i=0;i<emptyMatrix.length;i++) {
        	for (int j=0; j<emptyMatrix[0].length; j++) {
        		emptyMatrix[i][j] = matrix[i][j];	
        	}
        }
    }
    
    @Test
    public void begin(int[][] matrix, int startPlace, int destiny){
    	final int INF = Integer.MAX_VALUE;   // Valor m�ximo (infinito)
      
        int[][] shortpath;
        int[][] path = new int[5][5];

        matrix[0][0] = 0;
		matrix[0][1] = 30;
		matrix[0][2] = INF;
		matrix[0][3] = INF;
		matrix[0][4] = 15;
	
		matrix[1][0] = 30;
		matrix[1][1] = 0;
		matrix[1][2] = 25;
		matrix[1][3] = INF;
		matrix[1][4] = 40;
	
		matrix[2][0] = INF;
		matrix[2][1] = 25;
		matrix[2][2] = 0;
		matrix[2][3] = 15;
		matrix[2][4] = 70;
		
		matrix[3][0] = INF;
		matrix[3][1] = INF;
		matrix[3][2] = 15;
		matrix[3][3] = 0;
		matrix[3][4] = 90;

		matrix[4][0] = 15;
		matrix[4][1] = 40;
		matrix[4][2] = 70;
		matrix[4][3] = 90;
		matrix[4][4] = 0;
        for (int i=0; i<path.length; i++) {
        	for (int j=0; j<5; j++) {
        		if (matrix[i][j] == INF) {
                    path[i][j] = -1;	
        		}else {
        			path[i][j] = i;
        		}            
        	}
        }
                
    	for (int i=0; i<path.length; i++) {
    		path[i][i] = i;
    	}
        
    	
    	shortpath = shortestpath(matrix, path);
        // Prints out shortest distances.
        System.out.println("  0 1 2 3 4");
        System.out.println("  ---------");
    	for (int i=0; i<5;i++) {
            System.out.print(i + "|");
            for (int j=0; j<5;j++) {
            	System.out.print(shortpath[i][j]+" ");
            }
            System.out.println();
    	}
        System.out.println("\nRuta m�s corta de un v�rtice a otro (0 a 4)");

        String myPath = destiny + "";
        System.out.println();
        System.out.println("  0 1 2 3 4");
        System.out.println("  ---------");
         for (int i=0; i<path.length;i++) {
            System.out.print(i + "|");
            for (int j=0; j<path.length;j++) {
            	System.out.print(path[i][j]+" ");
            	// Test
            	assertEquals(5, path[i][j]);
            }
            System.out.println();
    	}
    	while (path[startPlace][destiny] != startPlace) {
    		if(path[startPlace][destiny] == -1) {
    			System.out.println("No es posible llegar.");
    			break;
    		}else {
    			myPath = path[startPlace][destiny] + " -> " + myPath;
                destiny = path[startPlace][destiny];	
    		}
            
    	}
    	myPath = startPlace + " -> " + myPath;
    	System.out.println("Esta es la ruta: " + myPath);
    	// Test
    	assertEquals(5, myPath);
    }
    
}
