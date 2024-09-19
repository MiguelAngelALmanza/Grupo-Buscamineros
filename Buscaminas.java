/*/public static void main(String []args){
    System.out.println("HOLA BUSCAMINAS");
    tablero(8);
}


public static void tablero(int x){
    int[][] board;
    board = new int[x][x];

    for(int i=0;i<x;i++){
        for(int j=0; j< x; j++){
            board[i][j] = 0;

        }
    }

    for(int i=0;i<x;i++){
        for(int j=0; j< x; j++){
            System.out.print(board[i][j]+" ");

        }
        System.out.print("\n");
    }

}

//*/