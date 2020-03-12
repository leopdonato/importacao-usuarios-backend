package com.leonardodonato.importacaousuarios.services;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.leonardodonato.importacaousuarios.domain.UsuariosIntermediario;
import com.leonardodonato.importacaousuarios.repository.UsuariosIntermediarioRepository;

@Service
public class UsuariosIntermediarioService {

	@Autowired
	private UsuariosIntermediarioRepository repo;

	public List<UsuariosIntermediario> findAll() {
		return repo.findAll();
	}

	public void insert(MultipartFile file) {
		try {
			List<UsuariosIntermediario> listaUsuariosIntermediario = new ArrayList<UsuariosIntermediario>();
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			XSSFSheet worksheet = workbook.getSheetAt(0);
			for (int i = 0; i < worksheet.getPhysicalNumberOfRows(); i++) {
				XSSFRow row = worksheet.getRow(i);
				if(row == null) continue;
				Integer id;
				String nome;
				String email;
				String dataNascimento;
				String sexo;
				Integer column = 0;

				XSSFCell cell = row.getCell(column);
				if(cell == null) continue;
				id = (int) Double.parseDouble(cell.toString());
				column++;
				cell = row.getCell(column);
				nome = cell.toString();
				column++;
				cell = row.getCell(column);
				email = cell.toString();
				column++;
				cell = row.getCell(column);
				dataNascimento = cell.toString();
				column++;
				cell = row.getCell(column);
				sexo = cell.toString();
				column++;

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date dataFormatada = sdf.parse(dataNascimento);

				if(id !=null && nome !=null && !nome.isEmpty() || email!=null && !email.isEmpty() || 
						dataFormatada!=null || sexo!=null && !sexo.isEmpty()) {
				UsuariosIntermediario user = new UsuariosIntermediario(null, nome, email, dataFormatada, sexo.charAt(0));
				user.setUserId(id);
				listaUsuariosIntermediario.add(user);
				} else {
					workbook.close();
					throw new DataIntegrityViolationException("Não foi possível inserir na tabela de validação!");
				}
			}
			workbook.close();
			repo.deleteAll();
			for (UsuariosIntermediario usuario : listaUsuariosIntermediario) {
				repo.save(usuario);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteAll() {
		try {
			repo.deleteAll();
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir tabela de validação!");
		}
	}
}
