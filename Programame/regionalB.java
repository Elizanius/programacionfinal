import java.io.BufferedReader;
import java.io.InputStreamReader;

public class regionalB {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
        
            Integer pruebas = Integer.parseInt(br.readLine());

            for (int i = 0; i < pruebas; i++) {
                String entrada = br.readLine();
                String[] ans = entrada.split("");
                Integer nunos = 0;
                Integer nceros = 0;

                for (int j = 0; j < entrada.length(); j++) {
                    if (entrada.charAt(j) == '1') {
                        nunos++;
                    }else{
                        nceros++;
                    }
                }
                
                if (ans[1].equals(ans[ans.length-1]) && ans[0].equals(ans[ans.length-2]) ) {
                    if (nunos == nceros) {
                        System.out.println("EQUILIBRADA");
                    }else{System.out.println("ERROR");}
                }else{
                    System.out.println("ERROR");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
