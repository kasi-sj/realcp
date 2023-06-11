import java.io.*;
import java.util.*;

public class Main {
    static FastReader fr ;
    static PrintWriter pw;
    public static void main(String[] args) {
        fr = new FastReader();
        pw = new PrintWriter(System.out);
        int t = fr.nextInt();
        while(t-->0) {
            start();
        }
        pw.flush();
    }

    private static void start(){
        int n = fr.nextInt();
        int k = fr.nextInt();
        int ans = n+k;
        pw.println(ans);
    }
}

/*<--------------------------------READER-------------------------------->*/
class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    double nextDouble() {
        return Double.parseDouble(next());
    }

    String nextLine() {
        String str = "";
        try {
            str = br.readLine();
        } catch (IOException e)  {
            e.printStackTrace();
        }
        return str;
    }
}

/*<--------------------------------BASIC_MATH-------------------------------->*/
class BMath{


    static int mod = (int)1e9+7;

    static int maxp = (int)1e7;
    static int ft[] = new int[(int)1e7];

    static {
        fact();
    }
    static long lcm(long x , long y){
        return (x*y)/gcd(x,y);
    }
    static long gcd(long x , long y){
        if(y==0)return x;
        return gcd(y,x%y);
        
    }

    static double log2(double x){
        return log(x)/log(2);
    }

    static double log10(double x){
        return log(x)/log(10);
    }

    static double log(double x){
        return Math.log(x);
    }

    static boolean isPrime(int x){

        if(x<=1)return false;
        if(x==2)return true;
        for(int i = 2 ; i*i <= x ; i++){
            if(x%i==0)return false;
        }
        return true;
    }

    static HashSet<Integer> primeFactor(int x){
        HashSet<Integer> hs = new HashSet<>();
        while(x%2==0){
            hs.add(2);
            x/=2;
        }

        for(int i = 3 ; i*i <= x ; i++){
            while (x%i==0){
                hs.add(i);
                x/=i;
            }
        }

        if(x>2)
            hs.add(x);

        return hs;
    }

    static int power(int a , int b){
        int ans = 1;
        while(b>0){
            if((b&1)!=0){
                ans = (int)((ans*(long)a)%mod);
            }
            a = (int)((a*(long)a)%mod);
            b>>=1;
        }
        return ans;
    }


    static void fact(){
        if(ft[0]==1)return ;
        ft[0]=1;
        for(int i = 1 ; i < ft.length ; i++){
            ft[i]=(int)((i*(long)ft[i-1])%mod);
        }
    }
    static int ncr(int n , int r){
        int ans = 1;
        int den = 1;
        for(int i = 0 ; i < r ; i++){
            ans = (int)((ans*(long)n--)%mod);
            den = (int)((den*(long)(i+1))%mod);
        }
        return (int)((ans*(long)power(den,mod-2))%mod);
    }

    static int npr(int n , int r){
        fact();
        int nem = ft[n];
        int den = ft[n-r];
        return (int)((nem*(long)power(den,mod-2))%mod);
    }
}

/*<--------------------------------MATRIX-------------------------------->*/
class Matrix{

    int n;
    int mat[][];

    static int mod = (int)1e9+7;
    Matrix(int  arr[][]){
        this.mat = arr;
        this.n = arr.length;
    }

    int[][] pow(long n){
        return power(n);
    }

    int [][] power(long n){
        if(n==1){
            int te[][] = new int[this.n][this.n];
            for(int i = 0 ; i < this.n ; i++)
                for(int j = 0 ; j < this.n ; j++)
                    te[i][j]=mat[i][j];
            return te;
        }
        int [][] temp = power(n/2);
        temp = mul(temp , temp);
        if(n%2==1){
            temp = mul(temp,mat);
        }
        return temp;
    }

    static int [][] mul(int [][]m2 , int [][]m1){
        int n = m1.length;
        int arr[][] = new int[n][n];
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                for(int k = 0 ; k < n ; k++){
                    arr[i][j]+=(int)((m1[i][k]*(long)m2[k][j])%mod);
                    arr[i][j]%=mod;
                }
            }
        }
        return arr;
    }
}

/*<--------------------------------SIEVE_OF_ERATOSTHENES-------------------------------->*/
class ertho{
    static boolean erth[];
    static int no = 1299722;
    static ArrayList<Integer> al ;

    static {
        erth = new boolean[no];
        al = new ArrayList<Integer>();
        Arrays.fill(erth,true);
        erth[0]=false;
        erth[1]=false;
        for(int i = 2 ; i*i < no ; i++){
            if(erth[i]){
                for(int j = i*i ; j<no ; j+=i){
                    erth[j]=false;
                }
            }
        }
        for(int i = 0 ; i <no ; i++)
            if(erth[i])
                al.add(i);
    }

    static int nthprime(int k){
        if(k<=al.size())
            return al.get(k-1);
        return -1;
    }
}

/*<--------------------------------PAIR-------------------------------->*/

class Pair implements Comparable{
    int first;
    int second;

    //String name;

    Pair(int first , int second,String name){
        this.first=first;
        this.second=second;
        //this.name=name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31*hash + first;
        hash = 31*hash + second;
        //hash = 32*hash + name==null?0:name.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        

        Pair user = (Pair) obj;
        return first == user.first
                //        && (name.equals(user.name)
                && second == user.second;
    }

    @Override
    public int compareTo(Object obj) {
        Pair user = (Pair)obj;
        int one = Integer.compare(first,user.first);
        int two = Integer.compare(first,user.second);
        //int the = name.compareTo(user.name);
        if(one==0)
            return two;
        return one;
    }
}

/*<--------------------------------DATA_STRUCTURE-------------------------------->*/

/*<--------------------------------DISJOINT_SET-------------------------------->*/

class DisjointSet{
    int size[];
    int pare[];
    int edge[];
    DisjointSet(int n){
        this.size = new int[n+1];
        this.pare = new int[n+1];
        this.edge = new int[n+1];
        for(int i = 0 ; i < n+1 ; i++)
            pare[i]=i;
        Arrays.fill(size,1);
    }

    int getparent(int n){
        if(pare[n]==n)return n;
        return pare[n]=getparent(pare[n]);
    }

    boolean insert(int i , int j){
        int pai = getparent(i);
        int paj = getparent(j);
        if(pai==paj){
            edge[pai]++;
            return false;
        }

        if(size[pai]>size[paj]){
            pare[paj] = pai;
            size[pai]+=size[paj];
            edge[pai]+=edge[paj];
            edge[pai]++;
        }else{
            pare[pai] = paj;
            size[paj]+=size[pai];
            edge[paj]+=edge[pai];
            edge[paj]++;
        }
        return true;
    }

}

/*<--------------------------------FENWICK_TREE-------------------------------->*/


class fenwick{
    int n;
    int arr[];
    int org[];
    fenwick(int n){
        this.n = n+1;
        this.arr = new int[n+1];
        this.org = new int[n+1];
    }

    void add(int i , int e){
        i++;
        while(i<n){
            arr[i]+=e;
            i+=(i&(-i));
        }
    }

    int sum(int i){
        i++;
        int sum = 0;
        while(i>0){
            sum+=arr[i];
            i-=(i&(-i));
        }
        return sum;
    }

    int range(int fr , int to){
        return sum(to)-sum(fr-1);
    }

    void update(int i , int e){
        i++;
        int up = e-org[i];
        org[i]=e;
        add(i-1,up);
    }
}

/*<--------------------------------SEGMENT_TREE-------------------------------->*/


class Root{
    int val;
    int left;
    int right;

    int update;

    boolean leaf;
    Root lroot;
    Root rroot;
    Root(int val,int left,int right){
        this.val=val;
        this.left=left;
        this.right=right;
        this.lroot=null;
        this.rroot=null;
        this.leaf = true;
        this.update = -1;
    }
}

class segmentRoot{
    int lo ;
    int hi ;

    Root node;
    segmentRoot(int lo , int hi){
        this.lo = lo;
        this.hi = hi;
        this.node = new Root(0,lo,hi);
    }

    public void add(int l ,int r ,int val){
        addNo(node,lo,hi,l,r,val);
    }

    public int val(int l , int r){
        return value(node,lo,hi,l,r);
    }
    public void setElement(int ind,int val){
        add(ind,ind,val);
    }

    public void setRange(int fr,int to,int val){
        add(fr,to,val);
    }

    public int getRange(int from , int to){
        return val(from,to);
    }

    public int getElement(int ind){
        return val(ind,ind);
    }
    void addNo(Root node , int lo , int hi , int l , int r , int val){
        if(node.leaf)add(node,lo,hi);
        if(node.update!=-1){
            node.val=node.update;
            node.lroot.update=node.update;
            node.rroot.update=node.update;
            node.update=-1;
        }
        if(lo>=l&&hi<=r){
            node.val = Math.max(val,node.val);
            node.lroot.update=node.val;
            node.rroot.update=node.val;
            return;
        }
        if(lo>r||hi<l)return;
        int mid = (lo+hi)/2;
        addNo(node.lroot,lo,mid,l,r,val);
        addNo(node.rroot,mid+1,hi,l,r,val);
        node.val=Math.max(node.lroot.val,  node.rroot.val);
    }

    int value(Root node , int lo , int hi , int l , int r){
        if(node.leaf)add(node,lo,hi);
        if(node.update!=-1){
            node.val=node.update;
            node.lroot.update=node.update;
            node.rroot.update=node.update;
            node.update=-1;
        }
        if(lo>=l&&hi<=r){
            return node.val;
        }

        int mid = (lo+hi)/2;
        int ans = 0;
        if(mid>=l)
            ans=Math.max(ans,value(node.lroot,lo,mid,l,r));
        if(mid+1<=r)
            ans=Math.max(ans,value(node.rroot,mid+1,hi,l,r));
        return ans;
    }
    void add(Root node , int lo , int hi){
        int mid = (lo+hi)/2;
        node.rroot = new Root(0,lo,mid);
        node.lroot = new Root(0,mid+1,hi);
        node.leaf = false;
    }
}

class segmentF{
    int arr[];
    HashMap<Integer,Integer> seg[];
    int n;

    segmentF(int arr[]){
        this.arr = arr;
        this.n = arr.length;
        this.seg = new HashMap[4*n];
        this.n = arr.length;
        query(0,0,n-1);
    }

    void query(int ind , int lo , int hi){
        if(hi==lo){
            seg[ind]=new HashMap<Integer,Integer>();
            seg[ind].merge(arr[lo],1,Integer::sum);
            return ;
        }
        int mid = (lo+hi)/2;
        query(2*ind+1,lo,mid);
        query(2*ind+2,mid+1,hi);
        seg[ind]=new HashMap<Integer,Integer>();
        for(Map.Entry<Integer,Integer> en : seg[2*ind+1].entrySet())
            seg[ind].merge(en.getKey(),en.getValue(),Integer::sum);
        for(Map.Entry<Integer,Integer> en : seg[2*ind+2].entrySet())
            seg[ind].merge(en.getKey(),en.getValue(),Integer::sum);
    }

    int range(int node , int left , int right){
        return frequency(0,0,n-1,left,right,node);
    }

    int frequency(int ind , int lo , int hi , int left, int right , int node){
        if(left<=lo&&hi<=right)
            return seg[ind].getOrDefault(node,0);
        if(left>hi||right<lo)
            return 0;
        int sum = 0;
        int mid = (lo+hi)/2;
        sum+=frequency(2*ind+1,lo,mid,left,right,node);
        sum+=frequency(2*ind+2,mid+1,hi,left,right,node);
        return sum;
    }
}

/*<--------------------------------SPARSE_TABLE-------------------------------->*/

class sparseTable{
    int n;
    int arr[];
    int table[][];
    int log;

    int alog[];
    sparseTable(int arr[]) {
        this.arr = arr;
        this.n = arr.length;
        this.log = (int) Math.ceil(Math.log(n) / Math.log(2))+1;
        this.table = new int[n][log];
        this.alog = new int[n+1];
        alog[1]=0;
        for(int i = 2 ; i <= n ; i++){
            alog[i]=alog[i/2]+1;
        }
        for(int i = 0 ; i < n ; i++){
            table[i][0] = arr[i];
        }
        for(int k = 1 ; k <= log ; k++){
            for(int i = 0 ; i+(1<<k) -1 < n ; i++){
                table[i][k] = Math.min(table[i][k-1],table[i+(1<<(k-1))][k-1]);

            }
        }
    }

    int minimum(int l , int r){
        l--;
        r--;
        int n = r-l+1;
        int k = alog[n];
        return Math.min(table[l][k],table[r-(1<<k)+1][k]);
    }
}

/*<--------------------------------TRIE-------------------------------->*/



class node{
    node[] link;
    boolean flag ;

    int val;
    node(){
        this.flag=false;
        this.link = new node[26];
        this.val = -1;
    }

    node get(char c){
        return link[c-'a'];
    }

    boolean has(char c){
        return link[c-'a']!=null;
    }

    void set(char c){
        link[c-'a']=new node();
    }
}


class Trie{
    node root;

    Trie(){
        this.root=new node();
    }

    void insert(String s){
        node temp = root;
        for(int i = 0 ; i < s.length() ; i++){
            if(!temp.has(s.charAt(i)))temp.set(s.charAt(i));
            temp=temp.get(s.charAt(i));
        }
        temp.flag=true;
    }

    int find(String s){
        node temp = root;
        for(int i = 0 ; i < s.length() ; i++){
            if(!temp.has(s.charAt(i)))return -1;
            temp=temp.get(s.charAt(i));
        }
        return temp.val;
    }
}

/*<--------------------------------END-------------------------------->*/
/*<---------------------DON'T TRY TO COPY PASTE 🤨-------------------->*/
