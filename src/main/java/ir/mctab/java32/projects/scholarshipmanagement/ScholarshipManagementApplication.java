package ir.mctab.java32.projects.scholarshipmanagement;

import ir.mctab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl.*;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.*;
import ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.impl.LoginUseCaseImpl;
import ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.usecases.LoginUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.model.Scholarship;
import ir.mctab.java32.projects.scholarshipmanagement.model.User;

import java.util.List;
import java.util.Scanner;

public class ScholarshipManagementApplication {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String command = "";
        while (!command.equals("exit")) {
            System.out.println("what do you want? ");
            command = scanner.nextLine();
            // Login
            if (command.equals("login")) {
                System.out.println("Username : ");
                String username = scanner.nextLine();
                System.out.println("Password : ");
                String password = scanner.nextLine();
                LoginUseCase loginUseCase = new LoginUseCaseImpl();
                User user = loginUseCase.login(username, password);
                if (user != null) {
                    System.out.println(" Login successful by " + user.getRole());
                }
            }
            if(command.equals("dashboard")){
                DashboardUseCase dashboardUseCase
                        = new DashboardUseCaseimpl();

                List<Scholarship> dashboard= dashboardUseCase.Dashboard();

            }

            else if(command.equals("request")){
                User loginUser = AuthenticationService.getInstance().getLoginUser();
                if (loginUser == null || !loginUser.getRole().equals("Student")){
                    System.out.println(" Please Login as Student!");
                }
                else {
                    System.out.print(" Name: ");
                    String name = scanner.nextLine();
                    System.out.print(" Family: ");
                    String family = scanner.nextLine();
                    String nationalCode = "";
                    do {
                        System.out.print(" National Code: ");
                        nationalCode = scanner.nextLine();
                        if (nationalCode.length() != 10)
                            System.out.println(" Invalid National Code Length!");
                    }while (nationalCode.length() != 10);

                    System.out.print(" Last University: ");
                    String lastUni = scanner.nextLine();
                    System.out.print(" Last Degree: ");
                    String lastDegree = scanner.nextLine();
                    System.out.print(" Last Field: ");
                    String lastField = scanner.nextLine();

                    String strScore;

                        System.out.print(" Last Score: ");
                        strScore = scanner.nextLine();

                    Float lastScore = Float.parseFloat(strScore);

                    System.out.print(" Apply University: ");
                    String applyUni = scanner.nextLine();
                    System.out.print(" Apply Degree: ");
                    String applyDegree = scanner.nextLine();
                    System.out.print(" Apply Field: ");
                    String applyField = scanner.nextLine();
                    System.out.print(" Apply Date: ");
                    String applyDate = scanner.nextLine();


                    Scholarship scholarship = new Scholarship(null, "RequestedByStudent", name, family, nationalCode
                            , lastUni, lastDegree, lastField, lastScore, applyUni, applyDegree, applyField, applyDate);

                    new RequestScholarshipByStudentUseCaseImpl().request(scholarship);
                    System.out.println(" Request Sent Successfully!");
                }

            }

            // find scholarship by supervisor
            if (command.equals("svlist")) {
                FindScholarshipBySupervisorUseCase findScholarshipBySupervisorUseCase
                        = new FindScholarshipBySupervisorUseCaseImpl();

                List<Scholarship> scholarships = findScholarshipBySupervisorUseCase
                        .listScholarships();
                for (int i = 0; i < scholarships.size(); i++) {
                    System.out.println(scholarships.get(i));
                }
            }
            if (command.equals("mnlist")) {
                FindScholarshipByManagerUseCase findScholarshipByManagerUseCase
                        = new FindScholarshipByManagerUseCaseImpl();
                List<Scholarship> scholarships = findScholarshipByManagerUseCase
                        .listScholarships();
                for (int i = 0; i < scholarships.size(); i++) {
                    System.out.println(scholarships.get(i));
                }
            }
            if (command.equals("unilist")) {
                FindScholarshipByUniversityUseCase findScholarshipByUniversityUseCase = new FindScholarshipByUniversityUseCaseImpl();
                List<Scholarship> scholarships = findScholarshipByUniversityUseCase
                        .listScholarships();
                for (int i = 0; i < scholarships.size(); i++) {
                    System.out.println(scholarships.get(i));
                }
            }
            // accept
            if (command.equals("svaccept")) {
                AcceptScholarshipBySupervisorUseCase acceptScholarshipBySupervisorUseCase
                        = new AcceptScholarshipBySupervisorUseCaseImpl();
                System.out.println("Scholarship Id: ");
                String scholarshipId = scanner.nextLine();
                acceptScholarshipBySupervisorUseCase.accept(Long.parseLong(scholarshipId));
                System.out.println("Done.");
            }
            if (command.equals("svreject")) {
                RejectScholarshipBySupervisorUseCase rejectScholarshipBySupervisorUseCase
                        = new RejectScholarshipBySupervisorUseCaseImpl();
                System.out.println("Scholarship Id: ");
                String scholarshipId = scanner.nextLine();
                rejectScholarshipBySupervisorUseCase.reject(Long.parseLong(scholarshipId));
                System.out.println("Reject Done.");
            }
            if (command.equals("mnaccept")) {
                AcceptScholarshipByManagerUseCase acceptScholarshipByManagerUseCase
                        = new AcceptScholarshipByManagerUseCaseImpl();
                System.out.println("Scholarship Id: ");
                String scholarshipId = scanner.nextLine();
                acceptScholarshipByManagerUseCase.accept(Long.parseLong(scholarshipId));
                System.out.println("Done.");
            }
            //regected
            if (command.equals("mnreject")) {
                RejectScholarshipByManagerUseCase rejectScholarshipByManagerUseCase
                        = new RejectScholarshipByManagerUseCaseImpl();
                System.out.println("Scholarship Id: ");
                String scholarshipId = scanner.nextLine();
                rejectScholarshipByManagerUseCase.reject(Long.parseLong(scholarshipId));
                System.out.println("Reject Done.");
            }
        }
    }
}
