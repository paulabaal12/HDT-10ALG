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
        // 2. Inicialice los pesos del "borde": inicialice de acuerdo con la entrada del usuario
        for (int i = 0; i < elen; i++) {
            // Lea el vértice inicial, el vértice final, el peso del borde
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
        
        // Inicializar "vértices" y "aristas"
        int vlen = vexs.length;

        // inicializar "vértice"
        mVexs = new char[vlen];
        for (int i = 0; i < mVexs.length; i++)
            mVexs[i] = vexs[i];

        // Inicializar "bordes"
        mMatrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++)
            for (int j = 0; j < vlen; j++)
                mMatrix[i][j] = matrix[i][j];

        // Estadísticas "borde"
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
        System.out.printf("Martix Graph:\n");
        for (int i = 0; i < mVexs.length; i++) {
            for (int j = 0; j < mVexs.length; j++)
                System.out.printf("%10d ", mMatrix[i][j]);
            System.out.printf("\n");
        }
    }
}
