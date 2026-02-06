package com.hr.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hr.entity.Compose;
import com.hr.repository.ComposeRepository;
import com.hr.repository.EmployeeRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ComposeRepository composeRepository;

    @GetMapping("/dash-board")
    public String dashboard(Model model, HttpSession session) {

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
        List<Compose> list = composeRepository.findAll();
        list.forEach(c ->
            employeeRepository.findById(c.getParentUkid())
                    .ifPresent(e -> c.setPosition(e.getDesignation()))
        );

        model.addAttribute("statusList", list);
        model.addAttribute("allEmployee", employeeRepository.findAll());

        return "dash-board";
    }

}
