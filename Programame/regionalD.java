import java.io.BufferedReader;
import java.io.InputStreamReader;

public class regionalD {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
        
            Integer pruebas = Integer.parseInt(br.readLine());
            
            for (int i = 0; i < pruebas; i++) {
                String enralla = "";
                int nX = 0;
                int nO = 0;
                
                for (int j = 0; j < 3; j++) {
                    enralla += br.readLine();

                }
                for (int j = 0; j < enralla.length(); j++) {
                    if (enralla.charAt(j) == 'X') {
                        nX++;
                    }else if(enralla.charAt(j) == 'O'){
                        nO++;
                    }
                }

                if(enralla.charAt(4) == '-'){
                    System.out.println("IMPOSIBLE");
                }else if(enralla.charAt(4) == 'X'){
                    if ((nO == nX) || (nO == nX-1)) {
                        System.out.println("CRUZ");
                    }else{System.out.println("IMPOSIBLE");}
                    
                }else if(enralla.charAt(4) == 'O'){
                    if ((nO == nX) || (nO-1 == nX)) {
                        System.out.println("CIRCULO");
                    }else{System.out.println("IMPOSIBLE");}
                    
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
