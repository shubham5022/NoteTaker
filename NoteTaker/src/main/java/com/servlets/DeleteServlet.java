package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transaction;

import org.hibernate.Session;

import com.entities.Note;
import com.helper.FactoryProvider;

public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			
			System.out.println("Inside delete..");
			
		int noteId = Integer.parseInt(request.getParameter("note_id").trim());	
		System.out.println("noteId::"+noteId);
		Session s = FactoryProvider.getFactory().openSession();
		org.hibernate.Transaction tx = s.beginTransaction();
		Note note = (Note)s.get(Note.class, noteId);
		s.delete(note);
		
		System.out.println("Transaction done!!");
		tx.commit();
		s.close();
		response.sendRedirect("all_notes.jsp");	
		}catch (Exception e) {
			
		}
	
	
	
	}

}
