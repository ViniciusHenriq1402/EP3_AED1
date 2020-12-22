package EP3_AED1.BST;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import EP3_AED1.BST.BinarySearchTree.IntComparator;


public class processa {
	
	 public void carrega(File folder1, File folder2){
		 	//arquivos de verificar(dir)
	    File[] fileNames1 = folder1.listFiles();
	    
	    //arquivos de entradas(dir)
	    File[] fileNames2 = folder2.listFiles();
	    
        if(folder1.isDirectory() && folder2.isDirectory()){
        	
        	//as duas pastas tem o mesmo tamanho
        	for(int i=0; i<fileNames1.length; i++ ){
        		
        		File file1 = fileNames1[i];
        		File file2 = fileNames2[i];
        		
                try {
                	makeLog(file1 ,file2);
                } catch (IOException e){
                  e.printStackTrace();
                }
            } 
        	
        } else {
            System.out.println("Os dois argumento devem indicar uma pasta");
        }
}
	 
	public void makeLog(File file1, File file2) throws IOException{
		
		BinarySearchTree<Integer, String> bst = new BinarySearchTree<Integer, String>(new IntComparator());
		 
		List<String> verif = new ArrayList<>();   
		List<String> lista = new ArrayList<>();
		List<Boolean> saida = new ArrayList<>();
	
		long start,end; 
		try (BufferedReader br = new BufferedReader(new FileReader(file1))) {
		
			//br returns as stream and convert it into a List
			verif = br.lines().collect(Collectors.toList());
			br.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		try (BufferedReader br = new BufferedReader(new FileReader(file2))) {
		
			lista = br.lines().collect(Collectors.toList());
			br.close();
			
		} catch (IOException e) {
		    e.printStackTrace();
		}
	
	
		System.out.println("------");
		System.out.println("Verificando " + file1.getName());
		
	
	
		start = System.currentTimeMillis();
		ProcessaBST(verif, lista, saida, bst);
		end = System.currentTimeMillis();
	
//		System.out.println((end-start));
		
		
		FileWriter writer = new FileWriter(file1.getName().replace(".txt","_bst.txt")); 
			for(boolean b: saida) {
				writer.write(b + System.lineSeparator());
		}
		    
		saida.clear();
		lista.clear();
		verif.clear();
	}

	private void ProcessaBST(List<String> verif, List<String> lista, List<Boolean> saida, BinarySearchTree<Integer, String> bst) {
		insereBST(lista, bst);
		buscaBST(verif, bst, saida);
	}

	private void buscaBST(List<String> verif, BinarySearchTree<Integer, String> bst, List<Boolean> saida) {
		Iterator<String> iter = verif.iterator(); 
		while (iter.hasNext()) { 
			String str = iter.next();
			Integer i = Integer.parseInt(str);
			//encontrou
			if(bst.find(i) != null) {
				saida.add(true);
			} else {
				//false = tarefa não foi para a impressora 1
				saida.add(false);
			}
		}  
	}

	private void insereBST(List<String> lista, BinarySearchTree<Integer, String> bst) {
		// TODO Auto-generated method stub
		Iterator<String> iter = lista.iterator(); 
		while (iter.hasNext()) { 
			String str = iter.next();
			if(str.length() != 0) {
				bst.insert(Integer.parseInt(str), str);
			}
		}  
	}

	
}