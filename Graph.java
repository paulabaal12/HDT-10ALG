//Obtenido de: https://mega.nz/file/KgBHmAga#7aspPOH5GduFxm5fXYyVqLtxdIFwpeCfMKs6Lj8YpNw

public class Graph {
    private int[][] shortestpath(int[][] adj, int[][] path){
        int n = adj.length;
        int[][] ans = new int[n][n];
        copy(ans, adj);
    	for (int k=0; k<n;k++) 
            for (int i=0; i<n; i++) {
            	for (int j=0; j<n;j++) {
            	    if (ans[i][k]+ans[k][j] < ans[i][j]) {
                        ans[i][j] = ans[i][k]+ans[k][j];
          		        path[i][j] = path[k][j];
                    }
            	}	
            }
        	

    	return ans;
    }

    private void copy(int[][] emptyMatrix, int[][] matrix){
        for (int i=0;i<emptyMatrix.length;i++) {
        	for (int j=0; j<emptyMatrix[0].length; j++) {
        		emptyMatrix[i][j] = matrix[i][j];	
        	}
        }
    }

    public void begin(int[][] matrix, int startPlace, int destiny){
    	final int INF = Integer.MAX_VALUE;   // valor maximo=infinito
        // Tests out algorithm with graph shown in class.
        int[][] shortpath;
        int[][] path = new int[5][5];

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
        System.out.println("\nRuta mas corta de un vertice a otro (0 a 4)");

         String myPath = destiny + "";
         System.out.println();
        System.out.println("  0 1 2 3 4");
        System.out.println("  ---------");
         for (int i=0; i<path.length;i++) {
            System.out.print(i + "|");
            for (int j=0; j<path.length;j++)
                System.out.print(path[i][j]+" ");
            System.out.println();
    	}
    	while (path[startPlace][destiny] != startPlace) {
    		if(path[startPlace][destiny] == -1) {
    			System.out.println("No es posible llegar a la ciudad :()");
    			break;
    		}else {
    			myPath = path[startPlace][destiny] + " -> " + myPath;
                destiny = path[startPlace][destiny];	
    		}
            
    	}
    	myPath = startPlace + " -> " + myPath;
    	System.out.println("Esta es la ruta: " + myPath);
    }
}
