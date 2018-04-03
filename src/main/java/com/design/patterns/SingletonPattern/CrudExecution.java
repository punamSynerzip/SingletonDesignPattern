package com.design.patterns.SingletonPattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class CrudExecution {
	public static void main(String[] args) throws SQLException, IOException {
		CrudOperations crud = new CrudOperations();
		
		int choice;
		int count = 1;
		String firstName, surname, position;
		int salary, id;
		String continueChar;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		do {
			System.out.println("\n\n\nCRUD operations on Database");
			System.out.println("..............................");
			System.out.println("1. Insert record");
			System.out.println("2. View records");
			System.out.println("3. Update record");
			System.out.println("4. Delete record");
			System.out.println("5. Exit");
			
			System.out.println("Please enter the choice for operation you want to perform: ");
			choice = Integer.parseInt(br.readLine());
			
			switch (choice) {
			case 1:
				System.out.println("Enter employee detials to insert....");
				System.out.println("First name");
				firstName = br.readLine();
				
				System.out.println("Surname");
				surname = br.readLine();
				
				System.out.println("Position");
				position = br.readLine();
				
				System.out.println("Salary");
				salary = Integer.parseInt(br.readLine());

				int insertCount = crud.insertData(firstName, surname, position, salary);
				if(insertCount > 0){
					System.out.println((count++) + " record has been inserted successfully..");
				}else{
					System.out.println("Data has not beed inserted");
				}
				break;

			case 2:
				System.out.println("Do you want to see all records? say Y/N ");
				String ans = br.readLine();
	
				if(ans.equalsIgnoreCase("y")){
					crud.getData(0);
				}else{
					System.out.println("Enter the employee id to view his/her record details");
					id = Integer.parseInt(br.readLine());
					crud.getData(id);	
				}
				break;
				
			case 3:
				System.out.println("Enter the emp id which you want to update details");
				id = Integer.parseInt(br.readLine());
				System.out.println("Enter the updated position of the employee");
				position = br.readLine();

				int updateCount = crud.updateData(id, position);
				if(updateCount > 0){
					System.out.println("record updated successfully..");
				}else{
					System.out.println("data has not been updated");
				}		
				break;
				
			case 4:
				System.out.println("Enter the emp id which you want to delete");
				id = Integer.parseInt(br.readLine());
				
				int deleteCount = crud.deleteData(id);
				if(deleteCount > 0){
					System.out.println("record deleted successfully..");
				}else{
					System.out.println("data has not been deleted");
				}	
				break;
				
			case 5:
				System.out.println("database closed..");
				break;
				
			default:
				System.out.println("You entered wrong operation choice");
			}
			
			System.out.println("Do you want to continue(y/n)?");
			continueChar = br.readLine();
		} while (choice != 5 && continueChar.equalsIgnoreCase("y"));
	}
	
}

