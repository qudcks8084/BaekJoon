import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[][] magic;
	static int n;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		magic = new int[n][n];
		
		if(n%2 == 1) {
			make_3();
		} 
		else if(n%4 == 0) {
			make_4();
		} 
		else if(n%4 == 2) {
			make_6();
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < n ; i++){
			for(int j = 0 ; j < n ; j++) {
				sb.append(magic[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static void make_3() {
		int r = 0;
		int c = n/2;
		for(int i = 1 ; i < n*n+1; i++) {
			magic[r][c] = i;
			
			int tmpR = r;
			int tmpC = c;
			
			if(r-1<0) r = n-1;
			else r--;
			
			if(c-1<0) c = n-1;
			else c--;
			
			if(magic[r][c] != 0) {
				r = tmpR+1;
				c = tmpC;
			}
		}
	}
	
	public static void make_4() {
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < n ; j++) {
				magic[i][j] = i * n + j + 1;
			}
		}
		
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < n ; j++) {
				if( (i > 0 || i < (n/4*3)) && (j >= n/4*1 &&  j < n/4*3)) {
					int tmp = n*n-magic[i][j]+1;
					magic[i][j] = tmp;
				}
				if( (j > 0 || j < (n/4*3)) && (i >= n/4*1 && i < n/4*3)) {
					int tmp = n*n-magic[i][j]+1;
					magic[i][j] = tmp;
				}
			}
		}
	}
	
	public static void make_6() {
		int m=n/2;
		for (int i = 0; i <m; i++) {
			for (int j = 0; j < m/2; j++) {
				if(i!=m/2){magic[i][j]=3;}
				else magic[i][j+1]=3;
			}
		}
		for (int i = 0; i <m; i++) {
			for (int j = 0; j < m; j++) {
				magic[i][j+m]=1;
			}
		}
		for (int i = 0; i <m; i++) {
			for (int j = 0; j < m-(m/2-1); j++) {
				magic[i][j+m]=2;
			}
		}
		for (int i = 0; i <m; i++) {
			for (int j = 0; j < m; j++) {
				if(magic[i][j]==0) {
					magic[i+m][j]=3;
				}else {
					magic[i+m][j]=0;
				}
				if(magic[i][j+m]==1) {
					magic[i+m][j+m]=2;
				}else {
					magic[i+m][j+m]=1;
				}
			}
		}
		for (int i = 0; i <n; i++) {
			for (int j = 0; j < n; j++) {
				magic[i][j]*=(m*m);
			}
		}
		int K=n/2;
		int[][] omd=new int[K][K];
		int r=0;
		int c=K/2;
		for (int i = 1; i < K*K+1; i++) {
			omd[r][c]=i;
			int tempR=r;
			int tempC=c;
			if(r-1<0) {
				r=K-1;
			}else {
				r--;
			}
			if(c-1<0) {
				c=K-1;
			}else {
				c--;
			}
			if(omd[r][c]!=0) {
				r=tempR+1;
				c=tempC;
			}
		}
		for (int i = 0; i <m; i++) {
			for (int j = 0; j < m; j++) {
				magic[i][j]+=omd[i][j];
				magic[i][j+m]+=omd[i][j];
				magic[i+m][j]+=omd[i][j];
				magic[i+m][j+m]+=omd[i][j];
			}
		}
		
	}
}
