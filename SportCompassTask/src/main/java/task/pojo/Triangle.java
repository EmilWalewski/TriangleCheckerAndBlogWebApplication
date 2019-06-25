package task.pojo;

import org.springframework.stereotype.Component;

import task.api.ITriangle;

@Component
public class Triangle implements ITriangle{
	
	String aParam, bParam, cParam;
	
	@Override
	public String checker() {
		
		int a, b, c;
		
		try {
			
			if(aParam.equals("") || bParam.equals("") || cParam.equals("")) return "Incorrect";
			
			a = Integer.parseInt(aParam);
			b = Integer.parseInt(bParam);
			c = Integer.parseInt(cParam);
			
			if(a+b < c || b+c < a || a+c < b) return "Incorrect";
			
			if(a != b && b != c && c != a) return "Scalene";
			if(a == b && b == c && c == a) return "Equilateral";
			if((a == b && c != a && c != b) || (a != b && b != c && c == a) || (a != b && b == c && c != a)) return "Isosceles";
			
			return "Incorrect";
			
		}catch(Exception e) {
			return "Incorrect";
		}
		
	}
	


	public String getaParam() {
		return aParam;
	}

	public void setaParam(String aParam) {
		this.aParam = aParam;
	}

	public String getbParam() {
		return bParam;
	}

	public void setbParam(String bParam) {
		this.bParam = bParam;
	}

	public String getcParam() {
		return cParam;
	}

	public void setcParam(String cParam) {
		this.cParam = cParam;
	}


}
