package taskfacil.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "User", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

	@Id @Column @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column
	private String name;

	@Column
	private String email;

	@Column
	private int password;

	private static final Pattern VALIDACAO_EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public User() {

	}

	public static boolean isEmail(String pUser){
		String[] mailAt = pUser.split("@");

		if(mailAt.length != 2){
			//System.out.println("1");
			return false;
		}else{

			if(mailAt[0].equals("") || mailAt[1].equals("")){
				//System.out.println("2");
				return false;
			}

			String[] domain = mailAt[1].split("\\.");

			if(domain.length < 2){
				//System.out.println("3");
				return false;
			}
		}
		return true;
	}

	public static boolean isEmail2(String pUser) {
        Matcher matcher = VALIDACAO_EMAIL .matcher(pUser);
        return matcher.find();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password.hashCode();
	}


}
