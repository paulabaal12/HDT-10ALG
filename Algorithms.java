import java.io.IOException;
import java.util.Scanner;

public class Algorithms {

    private int mEdgNum;        // numero de lados
    private char[] mVexs;       // Colección de vértices.
    private int[][] mMatrix;    // Matriz de adyacencia.
    private static final int INF = Integer.MAX_VALUE;   // Valor máximo (infinito)

    public Algorithms() {

        // Introduzca "Número de vértices" y "Número de aristas"
        System.out.printf("input vertex number: ");
        int vlen = readInt();
        System.out.printf("input edge number: ");
        int elen = readInt();
        if ( vlen < 1 || elen < 1 || (elen > (vlen*(vlen - 1)))) {
            System.out.printf("input error: invalid parameters :(\n");
            return ;
        }
        
        // inicializar "vértice"
        mVexs = new char[vlen];
        for (int i = 0; i < mVexs.length; i++) {
            System.out.printf("vertex(%d): ", i);
            mVexs[i] = readChar();
        }

        // 1. Inicializar los pesos del "borde"
        mEdgNum = elen;
        mMatrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                if (i==j)
                    mMatrix[i][j] = 0;
                else
                    mMatrix[i][j] = INF;
            }
        }
        // 2. inicializar los pesos del "borde": inicialice de acuerdo con la entrada del usuario
        for (int i = 0; i < elen; i++) {
            // leer el vértice inicial, el vértice final, el peso del borde
            System.out.printf("edge(%d):", i);
            char c1 = readChar();       // leer "iniciar vértice"
            char c2 = readChar();       // leer "vértice final"
            int weight = readInt();     // leer "peso"

            int p1 = getPosition(c1);
            int p2 = getPosition(c2);
            if (p1==-1 || p2==-1) {
                System.out.printf("input error: invalid edge :(\n");
                return ;
            }

            mMatrix[p1][p2] = weight;
            mMatrix[p2][p1] = weight;
        }
    }

    public Algorithms(char[] vexs, int[][] matrix) {
        
        // inicializar "vértices" y "aristas"
        int vlen = vexs.length;

        // inicializar "vértice"
        mVexs = new char[vlen];
        for (int i = 0; i < mVexs.length; i++)
            mVexs[i] = vexs[i];

        // inicializar "bordes"
        mMatrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++)
            for (int j = 0; j < vlen; j++)
                mMatrix[i][j] = matrix[i][j];

        // estadísticas "borde"
        mEdgNum = 0;
        for (int i = 0; i < vlen; i++)
            for (int j = i+1; j < vlen; j++)
                if (mMatrix[i][j]!=INF)
                    mEdgNum++;
    }

    private int getPosition(char ch) {
        for(int i=0; i<mVexs.length; i++)
            if(mVexs[i]==ch)
                return i;
        return -1;
    }

    private char readChar() {
        char ch='0';

        do {
            try {
                ch = (char)System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while(!((ch>='a'&&ch<='z') || (ch>='A'&&ch<='Z')));

        return ch;
    }

    private int readInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private int firstVertex(int v) {

        if (v<0 || v>(mVexs.length-1))
            return -1;

        for (int i = 0; i < mVexs.length; i++)
            if (mMatrix[v][i]!=0 && mMatrix[v][i]!=INF)
                return i;

        return -1;
    }

    private int nextVertex(int v, int w) {

        if (v<0 || v>(mVexs.length-1) || w<0 || w>(mVexs.length-1))
            return -1;

        for (int i = w + 1; i < mVexs.length; i++)
            if (mMatrix[v][i]!=0 && mMatrix[v][i]!=INF)
                return i;

        return -1;
    }

    private void DFS(int i, boolean[] visited) {

        visited[i] = true;
        System.out.printf("%c ", mVexs[i]);
        // atraviesa todos los vertices adyacentes de este vertice, si no ha visitado, entonces continúe bajando
        for (int w = firstVertex(i); w >= 0; w = nextVertex(i, w)) {
            if (!visited[w])
                DFS(w, visited);
        }
    }

    public void DFS() {
        boolean[] visited = new boolean[mVexs.length]; // marcador de acceso al vértice

        // marcador de acceso al vértice
        for (int i = 0; i < mVexs.length; i++)
            visited[i] = false;

        System.out.printf("DFS: ");
        for (int i = 0; i < mVexs.length; i++) {
            if (!visited[i])
                DFS(i, visited);
        }
        System.out.printf("\n");
    }

    public void BFS() {
        int head = 0;
        int rear = 0;
        int[] queue = new int[mVexs.length];            // cola de grupo auxiliar
        boolean[] visited = new boolean[mVexs.length];  // marcador de acceso al vértice

        for (int i = 0; i < mVexs.length; i++)
            visited[i] = false;

        System.out.printf("BFS: ");
        for (int i = 0; i < mVexs.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                System.out.printf("%c ", mVexs[i]);
                queue[rear++] = i;  // poner en cola
            }

            while (head != rear) {
                int j = queue[head++];  // sacar de la cola
                for (int k = firstVertex(j); k >= 0; k = nextVertex(j, k)) { //k son los vértices adyacentes visitados
                    if (!visited[k]) {
                        visited[k] = true;
                        System.out.printf("%c ", mVexs[k]);
                        queue[rear++] = k;
                    }
                }
            }
        }
        System.out.printf("\n");
    }

    public void print() {
        System.out.printf("Matrix Graph:\n");
        for (int i = 0; i < mVexs.length; i++) {
            for (int j = 0; j < mVexs.length; j++)
                System.out.printf("%10d ", mMatrix[i][j]);
            System.out.printf("\n");
        }
    }

    public void prim(int start) {
        int num = mVexs.length;         // número de vértices
        int index=0;                    
        char[] prims  = new char[num];  // matriz de resultados del árbol más pequeño prim
        int[] weights = new int[num];   // pesos de aristas entre vértices

        // el primer número en el árbol de expansión mínimo de prim es "el vértice de inicio en el gráfico" porque comienza desde el inicio.
        prims[index++] = mVexs[start];

        // inicializar "matriz de pesos de vértice"，
        // inicialice los pesos de cada vértice a los pesos de "start-th vertex" a "este vértice"
        for (int i = 0; i < num; i++ )
            weights[i] = mMatrix[start][i];
        // inicializar el peso del vértice de inicio a 0
        weights[start] = 0;

        for (int i = 0; i < num; i++) {
            if(start == i)
                continue;

            int j = 0;
            int k = 0;
            int min = INF;
            // entre los vértices no agregados al árbol de expansión mínimo, encuentre el vértice con el peso más pequeño
            while (j < num) {
                // pesos[j]=0, significa que "el j-ésimo nodo se ha ordenado" (o se ha agregado al árbol de expansión mínimo)
                if (weights[j] != 0 && weights[j] < min) {
                    min = weights[j];
                    k = j;
                }
                j++;
            }

            // después del procesamiento anterior, entre los vértices que no se han agregado al 
            // árbol de expansión mínimo, el vértice con el peso más pequeño es el k-ésimo vértice
            // agrega el k-ésimo vértice a la matriz de resultados del árbol de expansión mínimo
            prims[index++] = mVexs[k];
            // marcar el "peso del k-ésimo vértice" como 0 significa que el k-ésimo vértice ha sido ordenado (o agregado al resultado mínimo del árbol)
            weights[k] = 0;
            // después de agregar el k-ésimo vértice a la matriz de resultados del árbol de expansión mínimo, se actualizan los pesos de otros vértices
            for (j = 0 ; j < num; j++) {
                // se actualiza cuando el j-ésimo nodo no se ha procesado y debe actualizarse
                if (weights[j] != 0 && mMatrix[k][j] < weights[j])
                    weights[j] = mMatrix[k][j];
            }
        }

        // calcular los pesos del árbol de expansión mínimo
        int sum = 0;
        for (int i = 1; i < index; i++) {
            int min = INF;
            // obtener la posición de prims[i] en mMatrix
            int n = getPosition(prims[i]);
            for (int j = 0; j < i; j++) {
                int m = getPosition(prims[j]);
                if (mMatrix[m][n]<min)
                    min = mMatrix[m][n];
            }
            sum += min;
        }
        // imprimir el árbol de expansión mínimo
        System.out.printf("PRIM(%c)=%d: ", mVexs[start], sum);
        for (int i = 0; i < index; i++)
            System.out.printf("%c ", prims[i]);
        System.out.printf("\n");
    }

    public void kruskal() {
        int index = 0;                      // índice de la matriz de rets
        int[] vends = new int[mEdgNum];     // guardar el punto final de cada vértice en el "árbol de expansión mínimo existente" en el árbol mínimo.
        EData[] rets = new EData[mEdgNum];  
        EData[] edges;                      
        // obtener "todos los bordes en el gráfico"
        edges = getEdges();
        // ordenar los bordes según el tamaño del "peso" (de menor a mayor)
        sortEdges(edges, mEdgNum);

        for (int i=0; i<mEdgNum; i++) {
            int p1 = getPosition(edges[i].start);      // obtener el número de secuencia del "punto de partida" del i-ésimo borde
            int p2 = getPosition(edges[i].end);        // obtener el número de secuencia del "punto final" del i-ésimo borde

            int m = getEnd(vends, p1);                 // obtener el punto final de p1 en el "árbol de expansión mínimo existente"
            int n = getEnd(vends, p2);                 // obtener el punto final de p2 en el "árbol de expansión mínimo existente"
            // si m!=n, significa que "el borde i" no forma un ciclo con "vértices que se han agregado al árbol de expansión mínimo"
            if (m != n) {
                vends[m] = n;                       // establecer el punto final de m en el "árbol de expansión mínimo existente" en n
                rets[index++] = edges[i];           // guardar resultados
            }
        }

        int length = 0;
        for (int i = 0; i < index; i++)
            length += rets[i].weight;
        System.out.printf("Kruskal=%d: ", length);
        for (int i = 0; i < index; i++)
            System.out.printf("(%c,%c) ", rets[i].start, rets[i].end);
        System.out.printf("\n");
    }

    private EData[] getEdges() {
        int index=0;
        EData[] edges;

        edges = new EData[mEdgNum];
        for (int i=0; i < mVexs.length; i++) {
            for (int j=i+1; j < mVexs.length; j++) {
                if (mMatrix[i][j]!=INF) {
                    edges[index++] = new EData(mVexs[i], mVexs[j], mMatrix[i][j]);
                }
            }
        }

        return edges;
    }

    private void sortEdges(EData[] edges, int elen) {

        for (int i=0; i<elen; i++) {
            for (int j=i+1; j<elen; j++) {

                if (edges[i].weight > edges[j].weight) {
                    // intercambiar "borde i" y "borde j"
                    EData tmp = edges[i];
                    edges[i] = edges[j];
                    edges[j] = tmp;
                }
            }
        }
    }

    private int getEnd(int[] vends, int i) {
        while (vends[i] != 0)
            i = vends[i];
        return i;
    }

    public void floyd(int[][] path, int[][] dist) {

    	// Inicializar
        for (int i = 0; i < mVexs.length; i++) {
            for (int j = 0; j < mVexs.length; j++) {
                dist[i][j] = mMatrix[i][j];    
                path[i][j] = j;                
            }
        }

     // Calcula la ruta más corta
        for (int k = 0; k < mVexs.length; k++) {
            for (int i = 0; i < mVexs.length; i++) {
                for (int j = 0; j < mVexs.length; j++) {

                    int tmp = (dist[i][k]==INF || dist[k][j]==INF) ? INF : (dist[i][k] + dist[k][j]);
                    if (dist[i][j] > tmp) {
                        dist[i][j] = tmp;
                        path[i][j] = path[i][k];
                    }
                }
            }
        }

     // Imprime el resultado de la ruta más corta de Floyd
        System.out.printf("floyd: \n");
        for (int i = 0; i < mVexs.length; i++) {
            for (int j = 0; j < mVexs.length; j++)
                System.out.printf("%2d  ", dist[i][j]);
            System.out.printf("\n");
        }
    }

    // estructura de borde
    private static class EData {
        char start; // comienzo del borde
        char end;   // final del borde
        int weight; // peso del borde

        public EData(char start, char end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    };


    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int matrix[][] = {
                 /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
          /*A*/ {   0,  12, INF, INF, INF,  16,  14},
          /*B*/ {  12,   0,  10, INF, INF,   7, INF},
          /*C*/ { INF,  10,   0,   3,   5,   6, INF},
          /*D*/ { INF, INF,   3,   0,   4, INF, INF},
          /*E*/ { INF, INF,   5,   4,   0,   2,   8},
          /*F*/ {  16,   7,   6, INF,   2,   0,   9},
          /*G*/ {  14, INF, INF, INF,   8,   9,   0}};
        Algorithms pG;

        // "trama" personalizada (cola de matriz de entrada)
        // pG = nueva MatrizUDG();
        // Usar un "mapa" existente
        pG = new Algorithms	(vexs, matrix);

        //pG.print(); //imprime el grafo
        //pG.DFS(); // recorrido primero en profundidad
        //pG.BFS(); // recorrido primero en anchura
        //pG.prim(0); // El algoritmo prim genera un árbol de expansión mínimo
        //pG.kruskal(); // El algoritmo de Kruskal genera un árbol de expansión mínimo

        int[] prev = new int[pG.mVexs.length];
        int[] dist = new int[pG.mVexs.length];

        int[][] path = new int[pG.mVexs.length][pG.mVexs.length];
        int[][] floy = new int[pG.mVexs.length][pG.mVexs.length];
        pG.floyd(path, floy);
    }
}
