package br.com.caelum.fj31.jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class TesteUnMarshal {
	public static void main(String[] args) throws JAXBException {
			JAXBContext context = JAXBContext.newInstance(Produto.class);
			Unmarshaller unmarsh = context.createUnmarshaller();
			Produto produto = (Produto)unmarsh.unmarshal(new File("bola.xml"));
			
			System.out.println(produto.getCategoria().getNome());
			
	}
}
