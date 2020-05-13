import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;


class ContBancar{
	int balanta, tranzactiePrecedenta;
	String numeClient, idClient;
			
	ContBancar(String numeClient, String idClient) {
		this.numeClient=numeClient; 
		this.idClient=idClient;
		}
	
	void depozitare(int suma) {
		if (suma!=0) {
			balanta+=suma; 
			tranzactiePrecedenta=suma;
			}
		}
	
	void retragere(int suma) {
		if(suma!=0) {
			balanta-=suma; 
			tranzactiePrecedenta=-suma;
			}
		}
		
	void gettranzactiePrecedenta() {
		if (tranzactiePrecedenta>0) {
			System.out.println("S-a depozitat suma de: "+tranzactiePrecedenta);
			}
		else if (tranzactiePrecedenta<0) {
			System.out.println("S-a retras suma de: "+Math.abs(tranzactiePrecedenta));
			}
		else System.out.println("Nu s-a efectuat nici o tranzactie");}
	
	void listaMenu() {
		char variant;
		Scanner scan=new Scanner(System.in);
		System.out.println();
		System.out.println("Va uram bun venit dm/dmn: "+numeClient);
		System.out.println("ID-ul dmv este:"+idClient);
		
		
		do {
			System.out.println("========================================================================");
			System.out.println("Introduceti varianta\n1.Verificare cont bancar\n2.Depozitare suma\n"
					+ "3.Retragere suma\n4.Tranzactie precedenta\n5.Iesire meniu");
			System.out.println("========================================================================");
			variant=scan.next().charAt(0);
			System.out.println();
			
			switch(variant) {
			case '1':
				System.out.println("-------------------------------------------------------------------");
				System.out.println("Balanta contului este: "+balanta+"Lei");
				System.out.println("-------------------------------------------------------------------");
				System.out.println();
				break;
				
			case '2':
				System.out.println("-------------------------------------------------------------------");
				System.out.println("Introduceti suma pentru depozitare:");
				System.out.println("-------------------------------------------------------------------");
				int sum_dep=scan.nextInt(); depozitare(sum_dep);
				System.out.println();
				break;
			
			case '3':
				System.out.println("------------------------------------------------------------------");
				System.out.println("Introduceti suma spre retragere:");
				System.out.println("------------------------------------------------------------------");
				int sum_ret=scan.nextInt(); retragere(sum_ret);
				System.out.println();
				break;
				
			case '4':
				System.out.println("-----------------------------------------------------------------");
				gettranzactiePrecedenta();
				System.out.println("-----------------------------------------------------------------");
				System.out.println();
				break;
				
			case'5':
				System.out.println("****************************************************************");
				break;
				
			default:
				System.out.println("Varianta nevalida!!! Introduceti unul din 5 variante.");
				break;
			}
			
		} while (variant!='5'); System.out.println("Va multumim pentru folosirea serviciilor");
		
		scan.close();
	}
}

public class AppBank {
	public static void main(String[] args) {
		
		
		 BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
	        try {
	            System.out.print("Introduceti numele dmv: ");
	            String numeClient = obj.readLine();
	            System.out.print("Introduceti ID-ul dmv: ");
	            String idClient = obj.readLine();
	            ContBancar client = new ContBancar(numeClient, idClient);
	            client.listaMenu();
	        } catch (Exception e) {
	            System.out.println(e);
	        }
		
	}}

