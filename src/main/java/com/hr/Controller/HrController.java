package com.hr.Controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hr.entity.Employee;
import com.hr.repository.ComposeRepository;
import com.hr.repository.CreatePostRepository;
import com.hr.repository.EmployeeRepository;
import com.hr.HrManagemantPortalApplication;
import com.hr.entity.Compose;
import com.hr.entity.CreatePost;
import com.hr.service.HrService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class HrController {

    private final HrManagemantPortalApplication hrManagemantPortalApplication;
	
	@Autowired
	private HrService service;
	@Autowired
	private CreatePostRepository createPostRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ComposeRepository composeRepository;

    HrController(HrManagemantPortalApplication hrManagemantPortalApplication) {
        this.hrManagemantPortalApplication = hrManagemantPortalApplication;
    }

	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
	
	@GetMapping("/forgot-password")
	public String forgotPassword()
	{
		return "forgot-password";
	}
	
/*	@GetMapping("/home")
	public String home(@RequestParam("username") String username,@RequestParam("password") String password, Model model,HttpSession session) {
		System.out.println("User Name and Password:"+username+"-"+password);
		try {
	String empId=username.substring(3);
	System.out.println("empId:-" +empId);
Employee employee=employeeRepository.findByIdAndPassword(Integer.parseInt(empId), password);
		
		if(employee !=null) {
			model.addAttribute("error", false);
			
			session.setAttribute("userId", Integer.parseInt(empId));//after set then get goes to line no.108 my-profile mehod
			session.setAttribute("name", employee.getEmployeeName());//base page user login name line no.35
			session.setAttribute("desg", employee.getDesignation());//base page user designation line no.36
		
			if(employee.getRole().equals("USER")) {
					return "user-dashboard";

				}
				else if(employee.getRole().equals("ADMIN")) {
					List<Compose> findAll=composeRepository.findAll();
					findAll.stream()
							.forEach(k->{
								int id=k.getParentUkid();
						Optional<Employee>	optionalEmp=employeeRepository.findById(id);
						if(optionalEmp.isPresent()) {
							String designation=optionalEmp.get().getDesignation();
							 k.setPosition(designation);
						}else {
							 k.setPosition("Unknown");
						}
							});
					model.addAttribute("statusList", findAll);
					return "dash-board";
				}else {
					return "redirect:/login";
				}
				
			
		}else {
			model.addAttribute("error", true);
			return "login";
		}
		}catch(NumberFormatException e){
			//e.printStackTrace();
			System.err.println(e.getMessage());
			return "redirect:/login";
		}
		
	}*/
	
	
	@GetMapping("/home")
	public String home(
	        @RequestParam("username") String username,
	        @RequestParam("password") String password,
	        Model model,
	        HttpSession session) {

	    try {
	        String empId = username.substring(3);
	        Employee employee =
	                employeeRepository.findByIdAndPassword(
	                        Integer.parseInt(empId), password);

	        if (employee == null) {
	            model.addAttribute("error", true);
	            return "login";
	        }

	        // ✅ SESSION SET
	        session.setAttribute("userId", employee.getId());
	        session.setAttribute("name", employee.getEmployeeName());
	        session.setAttribute("desg", employee.getDesignation());
	        session.setAttribute("role", employee.getRole());

	        // ✅ ROLE BASED REDIRECT
	        if ("ADMIN".equals(employee.getRole())) {
	            return "redirect:/dash-board";
	        }

	        if ("USER".equals(employee.getRole())) {
	            return "redirect:/user-dashboard";
	        }

	        model.addAttribute("error", true);
	        return "login";

	    } catch (Exception e) {
	        return "redirect:/login";
	    }
	}

	
	
	

	
	/*@GetMapping("/dash-board")
	public String dashBoard() {
		return "dash-board";
	}*/
	
	
	
	@GetMapping("/add-employee")
	public String addEmployee(Model model) {
		model.addAttribute("employee", new Employee());
		return "add-employee";
		
	}
	
	@GetMapping("/all-employee")   
	public String allemployee(Model model ) {
		//get all record
		List<Employee> allEmployee=service.getAllEmployee();
		model.addAttribute("allEmployee", allEmployee);
		return "all-employee";
	}
	
	@GetMapping("/create-post")
	public String createpost(Model model) {   
	List<CreatePost> findAll=createPostRepository.findAll();
	model.addAttribute("post", findAll);
		return "create-post";
	
}
	@GetMapping("/status")
	public String status(Model model) {
		
		List<Compose> findAll=composeRepository.findAll();
		findAll.stream()
				.forEach(k->{
					int id=k.getParentUkid();
			Optional<Employee>	optionalEmp=employeeRepository.findById(id);
			if(optionalEmp.isPresent()) {
				String designation=optionalEmp.get().getDesignation();
				 k.setPosition(designation);
			}else {
				 k.setPosition("Unknown");
			}
				});
		model.addAttribute("statusList", findAll);

		return "status";
	
}
	
	@GetMapping("/my-profile")
	public String myprofile(HttpSession session,Model model) {
		
	Object	attribute=session.getAttribute("userId");//my-profile user data shows
    int	userId=Integer.parseInt(attribute.toString());//my-profile user data shows
  Employee  employee=employeeRepository.findById(userId).get();//my-profile user data shows
  model.addAttribute("employee", employee);//my-profile user data shows
	System.out.println("Object:-"+attribute);
	
		return "my-profile";
		
	
}
	
	@GetMapping("/setting")
	public String setting() {
		return "setting";
}
	
	@PostMapping("/save-employee")
	public String saveEmployee(@Valid @ModelAttribute Employee employee,BindingResult result,Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("employee", employee);
			return"add-employee";
		}
		
		employee.setPassword(employee.getDateOfBirth());
		Employee save=service.addEmployee(employee);
		return "redirect:/all-employee";
	}
	
	@PostMapping("/save-post")
	public String savePost(@ModelAttribute CreatePost createPost) {
		createPost.setAddedDate(new Date().toString());//LocalDateTime.now().toString()
		CreatePost addPost=service.addPost(createPost);
		return "redirect:/create-post";
	}
	
	@PostMapping("/update-password")
	public String updatePassword(@RequestParam("password")String password,@RequestParam("newPassword1")String newPassword1,@RequestParam("newPassword2")String newPassword2,HttpSession session,Model model) {
		System.out.println(password+" - "+newPassword1+" - "+newPassword2);
		
		Object	attribute=session.getAttribute("userId");
	    int	userId=Integer.parseInt(attribute.toString());
	  Employee  employee=employeeRepository.findByIdAndPassword(userId,password);
		
		if(employee!=null && newPassword1.equals(newPassword2)) {
			
			employee.setPassword(newPassword2);
			employeeRepository.save(employee);
			model.addAttribute("error", false);
		}else {
			model.addAttribute("error", true);
			return"/setting";
		}
		return"redirect:/login";
	}
	
	
	@GetMapping("/edit-record")
	public String editRecord(@RequestParam("id")int id,Model model) {
		System.out.println("ID:-"+id);
	Employee employee=employeeRepository.findById(id).get();
	          model.addAttribute("employee", employee);
		return "edit-record";
	}
	
	@PostMapping("/edit-employee")
	public String updateRecord(@ModelAttribute Employee employee) {
		int id=employee.getId();
	Employee getEmp=employeeRepository.findById(id).get();
	if(getEmp!=null) {
		employeeRepository.save(employee);
	}
		return "redirect:/all-employee";
		
	}
	
	@GetMapping("/deleteRecord-byId")
	public String deleteRecordById(@RequestParam("id")int id) {
		employeeRepository.deleteById(id);
		return "redirect:/all-employee";
	}
	
	/*@GetMapping("/user-dashboard")
	public String userDashboard(Model model,HttpSession session) {
		
		Object	attribute=session.getAttribute("userId");
	    int	userId=Integer.parseInt(attribute.toString());
		
		List<Compose> findAll=composeRepository.findByParentUkid(userId);
		findAll.stream()
				.forEach(k->{
					int id=k.getParentUkid();
					 String designation=employeeRepository.findById(id).get().getDesignation();
					   
					  k.setPosition(designation);
				});
		model.addAttribute("statusList", findAll);
		return "user-dashboard";
	}*/
	
	
	
    @GetMapping("/user-dashboard")
    public String userDashboard(Model model, HttpSession session) {

        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        // Department counts
        model.addAttribute("devCount", employeeRepository.countByDepartment("DEVELOPMENT"));
        model.addAttribute("qaCount", employeeRepository.countByDepartment("QA"));
        model.addAttribute("netCount", employeeRepository.countByDepartment("NETWORKING"));
        model.addAttribute("hrCount", employeeRepository.countByDepartment("HR"));
        model.addAttribute("secCount", employeeRepository.countByDepartment("SECURITY"));
        model.addAttribute("salesCount", employeeRepository.countByDepartment("SALES"));

        // Status counts
        model.addAttribute("pendingCount", composeRepository.countByStatus("PENDING"));
        model.addAttribute("approvedCount", composeRepository.countByStatus("APPROVED"));
        model.addAttribute("canceledCount", composeRepository.countByStatus("CANCELED"));
        model.addAttribute("deniedCount", composeRepository.countByStatus("DENIED"));
        model.addAttribute("allCount", composeRepository.count());

        // Recent activity  
        Object	attribute=session.getAttribute("userId");
	    int	userId=Integer.parseInt(attribute.toString());
		
		List<Compose> findAll=composeRepository.findByParentUkid(userId);
		findAll.stream()
				.forEach(k->{
					int id=k.getParentUkid();
					 String designation=employeeRepository.findById(id).get().getDesignation();
					   
					  k.setPosition(designation);
				});
		model.addAttribute("statusList", findAll);
		 model.addAttribute("allEmployee", employeeRepository.findAll());
		return "user-dashboard";
    }
	
	
	
	/*@GetMapping("/user-dashboard")
	public String userDashboard(Model model, HttpSession session) {

	    Object attribute = session.getAttribute("userId");
	    int userId = Integer.parseInt(attribute.toString());

	    // Fetch all compose records for this user
	    List<Compose> findAll = composeRepository.findByParentUkid(userId);
	    
	    // Set the position (designation)
	    findAll.forEach(compose -> {
	        int id = compose.getParentUkid();
	        String designation = employeeRepository.findById(id)
	                                .map(Employee::getDesignation)
	                                .orElse("Unknown");
	        compose.setPosition(designation);
	    });

	    model.addAttribute("statusList", findAll);

	    // Count by department
	    Map<String, Long> deptCount = findAll.stream()
	        .collect(Collectors.groupingBy(Compose::getPosition, Collectors.counting()));
	    model.addAttribute("deptCount", deptCount);

	    // Count by status
	    Map<String, Long> statusCount = findAll.stream()
	        .collect(Collectors.groupingBy(Compose::getStatus, Collectors.counting()));
	    model.addAttribute("statusCount", statusCount);

	    return "user-dashboard";
	}*/

	
	
	@GetMapping("/user-profile")
	public String userprofile(HttpSession session,Model model) {
		
	Object	attribute=session.getAttribute("userId");
    int	userId=Integer.parseInt(attribute.toString());
  Employee  employee=employeeRepository.findById(userId).get();
  model.addAttribute("employee", employee);
	System.out.println("Object:-"+attribute);
	
		return "user-profile";
}
	
	@GetMapping("/user-setting") 
	public String usersetting() {
		return "user-setting";
}
	
	@GetMapping("/user-compose")
	public String usercompose() {
		return "user-compose";
}
	
	@PostMapping("/compose")
	public String addCompose(@RequestParam("subject")String subject,@RequestParam("text")String text,HttpSession session) {
		
		try {
			Object	attribute=session.getAttribute("userId");
			int	userId=Integer.parseInt(attribute.toString());
  Employee employee= employeeRepository.findById(userId).get();
			Compose compose=new Compose();
			compose.setEmployeeName(employee.getEmployeeName());
			compose.setSubject(subject);
			compose.setText(text);
			compose.setParentUkid(userId);
			compose.setAddedDate(new Date().toString());
			compose.setStatus("PENDING");
			composeRepository.save(compose);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return"redirect:/user-compose";
	}
	
	@GetMapping("/approve-byId")
	public String approved(@RequestParam("id") int id,@RequestParam("type") String type) {
		System.out.println(id+" === "+type);
	Compose	compose=composeRepository.findById(id).get();
	compose.setStatus(type);
	composeRepository.save(compose);
		return "redirect:/status";
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
