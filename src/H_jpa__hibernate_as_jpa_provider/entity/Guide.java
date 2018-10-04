package H_jpa__hibernate_as_jpa_provider.entity;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@BatchSize(size=3)
public class Guide {

	public Guide() {
	}

	public Guide(String staffId, String name, Integer salary) {
		this.staffId = staffId;
		this.name = name;
		this.salary = salary;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "staff_id", nullable = false)
	private String staffId;
	private String name;
	private Integer salary;

	@OneToMany(mappedBy="guide", cascade={CascadeType.PERSIST}, fetch = FetchType.LAZY)
	private Set<Student> students = new HashSet<Student>();


	public Set<Student> getStudents() {
		return students;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public void addStudent(Student student) {
		students.add(student);
		student.setGuide(this);
	}

}