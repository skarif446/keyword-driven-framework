package mypack;
import java.io.File;
import java.lang.reflect.Method;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
public class GmailRunner 
{
	public static void main(String[] args) throws Exception
	{
		//Open excel file for reading
        File f=new File("gmailtests.xls");
        Workbook rwb=Workbook.getWorkbook(f);
		Sheet rsh1=rwb.getSheet(0); //sheet1 with tests
		int not=rsh1.getRows();
		int nouc1=rsh1.getColumns();
		Sheet rsh2=rwb.getSheet(1); //sheet2 with steps
		int nos=rsh2.getRows();
		int nouc2=rsh2.getColumns();
		//Open same excel file for writing
		WritableWorkbook wwb=Workbook.createWorkbook(f,rwb);
		WritableSheet wsh1=wwb.getSheet(0); //sheet1 for results
		WritableSheet wsh2=wwb.getSheet(1); //sheet2 for results
		//Create object to methods class
		Gmailmethods gm=new Gmailmethods();
		//Collect methods information using methods class object
		Method m[]=gm.getClass().getMethods();
		//Calling methods one after other
		//1st row(index is 0) have names of columns in Sheet1
		for(int i=1;i<not;i++)
		{
			int flag=0;
			String tid=rsh1.getCell(0,i).getContents();
			String mode=rsh1.getCell(2,i).getContents();
			if(mode.equalsIgnoreCase("yes"))
			{
				//1st row(index is 0) have names of cols in Sheet2
				for(int j=1;j<nos;j++)
				{
					String sid=rsh2.getCell(0,j).getContents();
					if(tid.equalsIgnoreCase(sid))
					{
						String mn=rsh2.getCell(2,j).getContents();
						String o=rsh2.getCell(3,j).getContents();
						String d=rsh2.getCell(4,j).getContents();
						String c=rsh2.getCell(5,j).getContents();
						System.out.println(mn+" "+o+" "+d+" "+c);
						for(int k=0;k<m.length;k++)
						{
							if(m[k].getName().equals(mn))
							{
								String r=(String) m[k].invoke(gm,o,d,c);
								if(r.contains("failed") || 
										    r.contains("interrupted"))
								{
									flag=1;  
									
								}
								Label l=new Label(nouc2,j,r);
								wsh2.addCell(l);
							}
						}
					}
				}
				if(flag==0)
				{
					Label l=new Label(nouc1,i,"Passed");
					wsh1.addCell(l);
				}
				else
				{
					Label l=new Label(nouc1,i,"Failed");
					wsh1.addCell(l);
				}
			}
		}
		//Save and close excel
		wwb.write();
		wwb.close();
		rwb.close();
	}
}