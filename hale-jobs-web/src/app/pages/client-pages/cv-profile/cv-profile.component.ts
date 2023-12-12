import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzModalService } from 'ng-zorro-antd/modal';
import { NgxSpinnerService } from 'ngx-spinner';
import { Subscription } from 'rxjs';
import { User } from 'src/app/models/core/user.model';
import { AuthService } from 'src/app/services/auth.service';
import { CVUserService } from 'src/app/services/cv-user.service';
import { GenericService } from 'src/app/services/generic.service';

@Component({
  selector: 'app-cv-profile',
  templateUrl: './cv-profile.component.html',
  styleUrls: ['./cv-profile.component.css']
})
export class CvProfileComponent implements OnInit {


  public user: User| undefined;
  public userId : any;
  public isLoadingGeneral = false;
  public subcriptions: Subscription[] = [];
  
  //VARIABLES COMPARTIDAS

  public userInformationOriginal : any;
  public userInformation : any;


  //VARIABLES DE INFORMACIÓN DE USUARIO ------------------------------------------

  public visibleInfoPersonal = false;
  public isLoadinginfoPersonal = false;


  public visibleAddExperience = false;
  public visibleEditExperience = false;

  public listStates : any = [];
  public listCities : any = [];
  public listSubcategories : any = [];
  public levelStudies : any = [];


  public listWorkExperiences : any = [];
  public loadingListWorkExperiences = false;


  public listSkills : any = [];
  public listSkillsEdit : any = [];
  public isLoadingSkillsSave : boolean = false;


  public workExperienceEdit : any;
  public isLoadingWorkExperience : boolean = false;


  public listSchools : any;
  public listCertifications : any;
  public certificationEdit : any;
  public schoolEdit : any; 


  public skills: any = [
    {
      idSkill: 1,
      skillName: 'DESARROLLO WEB'
    },
    {
      idSkill: 2,
      skillName: 'DESARROLLO MOVIL'
    },
    {
      idSkill: 3,
      skillName: 'TESTER'
    },
  ]
  


  userInfoPersonalForm = new FormGroup({
    jobTitle: new FormControl(null, [Validators.required, Validators.minLength(8), Validators.maxLength(50)]),
    salary: new FormControl(null, [Validators.required, Validators.maxLength(50)]),
    aboutMe: new FormControl(null, Validators.required),
  });

  //VARIABLES EXPERIENCIA USUARIO ------------------------------------------------------

  public idExperienceProfessional = 0;



  registerRecruiterExperienceProfessionalForm = new FormGroup({
    job: new FormControl(null, [Validators.required, Validators.minLength(8), Validators.maxLength(50)]),
    company: new FormControl(null, Validators.required),
    skills: new FormControl(null, Validators.required),
    begins: new FormControl(null, Validators.required),
    ends: new FormControl(null, Validators.required),
    description: new FormControl(null, [Validators.required, Validators.minLength(50)]),
  });

  //VARIABLES HABILIDADES USUARIO ----------------------------------------------------------
  public visibleAddSkills = false;

  public skillsTemporary: any = [];
  
  public schools: any = [
    {
      idSchool: 1,
      schoolName: 'Universidad Autónoma del Carmen'
    },
    {
      idSchool: 2,
      schoolName: 'Universidad Mesoamericana de San Agustín'
    },
    {
      idSchool: 3,
      schoolName: 'EDUCREA'
    },
  ]

  public levelSchool: any = [
    {
      idLevel: 1,
      levelName: 'Universitario - No titulado en Tecnologías de Información'
    },
    {
      idLevel: 2,
      levelName: 'Universitario - Titulado'
    },
    {
      idLevel: 3,
      levelName: 'CARRERA TÉCNICA'
    },
  ]

  skillsForm = new FormGroup({
    skills: new FormControl([], Validators.required),
  });

  //VARIABLES EDUCACION DE USUARIO ----------------------------------------------------------
  public visibleAddEducation = false;
  public idEducationUser = 0;

  public educationUser: any = [];

  EducationUserForm = new FormGroup({
    nameSchool: new FormControl(null, [Validators.required, Validators.minLength(8), Validators.maxLength(50)]),
    levelSchool: new FormControl(null, Validators.required),
    startDate: new FormControl(null, Validators.required),
    endDate: new FormControl(null, Validators.required),
  });

  //VARIABLES CERTIFICACIÓN DE USUARIO ----------------------------------------------------------
  public visibleAddCertificate = false;
  public idCertificateUser = 0;

  public certificateUserArray: any = [{
    nameCertificate: 'Universidad Autónoma del Carmen',
    levelCertificate: 'Universitario - No titulado en Tecnologías de Información',
    startDate: new Date(),
    endDate: new Date(),
  }];

  CertificateUserForm = new FormGroup({
    nameCertificate: new FormControl(null, [Validators.required, Validators.minLength(8), Validators.maxLength(50)]),
    levelCertificate: new FormControl(null, Validators.required),
    startDate: new FormControl(null, Validators.required),
    endDate: new FormControl(null, Validators.required),
  });

  constructor(
    private modalService: NzModalService,
    private cvService : CVUserService,
    private genericService : GenericService,
    private authenticationService: AuthService,
    private message: NzMessageService,
    private router: Router,
    private ngxSpinner: NgxSpinnerService 
  ) { }

  ngOnInit(): void {

    if (this.authenticationService.isUserLoggedIn()) {
      this.user = this.authenticationService.getUserFromLocalCache();
      this.userId = this.user.id;
      this.getCurrentUser();
      this.getStates();
      this.getLevelOfStudy();
      this.getSubcategories();  
      this.getWorkExperiencesByUser();
      this.getskillsByUser();
      this.getSchoolsByUser();
      this.getCertificationsByUser();
    } else {
      this.router.navigateByUrl("/auth/login");
    }

  }

  // Servicios API
  getCurrentUser() {
    this.ngxSpinner.show();
    this.isLoadingGeneral = true;
    this.authenticationService.getCurrentUser("bicosind@gmail.com").subscribe(
      (response: any) => {

       this.userInformation = response;
       this.userInformationOriginal = this.userInformation;

        this.isLoadingGeneral = false;  
        this.ngxSpinner.hide();     
      },
      (errorResponse: HttpErrorResponse) => {
        this.message.create("error", 'Ha ocurrido un error al recuperar los estados');
        this.isLoadingGeneral = false;
        this.ngxSpinner.hide();     
        
      }
    )
  }

  getWorkExperiencesByUser() {
    this.loadingListWorkExperiences = true;
    this.ngxSpinner.show();
    this.cvService.workExperiencesByUser(1).subscribe(
      (response: any) => {  
        this.listWorkExperiences = response;
        this.loadingListWorkExperiences = false;
        this.ngxSpinner.hide();       
      },
      (errorResponse: HttpErrorResponse) => {
        this.message.create("error", 'Ha ocurrido un error al recuperar las experiencias de trabajo');
        this.loadingListWorkExperiences = false;
        this.ngxSpinner.hide();
      }
    )
  }


  getWorkExperienceById(id : any) {
    this.isLoadingWorkExperience = true;
    this.cvService.workExperiencesById(id).subscribe(
      (response: any) => {
  
        console.log(response);
        this.workExperienceEdit = response;
        this.isLoadingWorkExperience = false;       
      },
      (errorResponse: HttpErrorResponse) => {
        this.message.create("error", 'Ha ocurrido un error al recuperar las experiencias de trabajo');
        this.isLoadingWorkExperience = false;
      }
    )
  }


  getskillsByUser() {
    this.isLoadingGeneral = true;
    this.cvService.getSkillsByUser(1).subscribe(
      (response: any) => {
       this.listSkills = [];
       this.listSkillsEdit = [];
       response.forEach((prop: any, key: any) => {
          this.listSkills.push(prop.value)   
        }); 

        
        this.isLoadingGeneral = false;       
      },
      (errorResponse: HttpErrorResponse) => {
        this.message.create("error", 'Ha ocurrido un error al recuperar las skills');
        this.isLoadingGeneral = false;
      }
    )
  }



  getSchoolsByUser() {
    this.isLoadingGeneral = true;
    this.cvService.getSchoolsByUser(1).subscribe(
      (response: any) => {
       this.listSchools = response;
        this.isLoadingGeneral = false;       
      },
      (errorResponse: HttpErrorResponse) => {
        this.message.create("error", 'Ha ocurrido un error al recuperar las escuelas');
        this.isLoadingGeneral = false;
      }
    )
  }

  getSchoolsById() {
    this.isLoadingGeneral = true;
    this.genericService.getAllStates().subscribe(
      (response: any) => {
       this.schoolEdit = response;
        this.isLoadingGeneral = false;       
      },
      (errorResponse: HttpErrorResponse) => {
        this.message.create("error", 'Ha ocurrido un error al recuperar las escuelas');
        this.isLoadingGeneral = false;
      }
    )
  }

  getCertificationsByUser() {
    this.isLoadingGeneral = true;
    this.cvService.getCertificationsByUser(1).subscribe(
      (response: any) => {
       this.listCertifications = response;
        this.isLoadingGeneral = false;       
      },
      (errorResponse: HttpErrorResponse) => {
        this.message.create("error", 'Ha ocurrido un error al recuperar las certificaciones');
        this.isLoadingGeneral = false;
      }
    )
  }

  getCertificationById() {
    this.isLoadingGeneral = true;
    this.genericService.getAllStates().subscribe(
      (response: any) => {
       this.certificationEdit = response;
        this.isLoadingGeneral = false;       
      },
      (errorResponse: HttpErrorResponse) => {
        this.message.create("error", 'Ha ocurrido un error al recuperar la certificación');
        this.isLoadingGeneral = false;
      }
    )
  }
    
  getStates() {
    this.isLoadingGeneral = true;
    this.genericService.getAllStates().subscribe(
      (response: any) => {
        this.listStates = response.map((prop: any, key: any) => {
          return {
            ...prop,
            key: key + 1,
          };
        }); 
        this.isLoadingGeneral = false;       
      },
      (errorResponse: HttpErrorResponse) => {
        this.message.create("error", 'Ha ocurrido un error al recuperar los estados');
        this.isLoadingGeneral = false;
      }
    )
  }


  getCities(p : any) {
    this.isLoadingGeneral = true;
    this.genericService.getAllCities(p).subscribe(
      (response: any) => {
        this.listCities = response.map((prop: any, key: any) => {
          return {
            ...prop,
            key: key + 1,
          };
        });        
        this.isLoadingGeneral = false;
      },
      (errorResponse: HttpErrorResponse) => {
        this.message.create("error", 'Ha ocurrido un error al recuperar los municipios');
        this.isLoadingGeneral = false;
      }
    )
  }

  getSubcategories() {
    this.isLoadingGeneral = true;
    this.genericService.getAllSubcategoriesByCategory().subscribe(
      (response: any) => {
        this.listSubcategories = response.map((prop: any, key: any) => {
          return {
            ...prop,
            key: key + 1,
          };
        }); 
        this.isLoadingGeneral = false;       
      },
      (errorResponse: HttpErrorResponse) => {
        this.isLoadingGeneral = false;
        this.message.create("error", 'Ha ocurrido un error al recuperar los municipios');
      }
    )
  }


  getLevelOfStudy() {
    this.isLoadingGeneral = true;
    this.genericService.getAllTypeOfLevelStudy().subscribe(
      (response: any) => {
       console.log(response);
        this.levelStudies = response.map((prop: any, key: any) => {
          return {
            ...prop,
            key: key + 1,
          };
        });        
        this.isLoadingGeneral=false;
      },
      (errorResponse: HttpErrorResponse) => {
        this.message.create("error", 'Ha ocurrido un error al recuperar los municipios');
        this.isLoadingGeneral=false;
      }
    )
  }













  // ------------------------------------------------- Lógica de negocio ------------------------------------------------

  //FUNCIONES DE INFORMACIÓN PERSONAL DE USUARIO --------------------------------------------------

  showModalInfoUser(): void {
    this.visibleInfoPersonal = true;
  }

  closeModalInfoUser() {
    this.visibleInfoPersonal = true;
  }


  closeModal(): void {
    //CERRAMOS MODAL
    this.visibleInfoPersonal = false;
  }

  handleOk(): void {
    console.log('Button ok clicked!');
    this.visibleInfoPersonal = false;
  }

  saveInfoUser(): void {
    if (!this.userInfoPersonalForm.valid) {
      Object.values(this.userInfoPersonalForm.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
      return;
    }

    let data = this.userInfoPersonalForm.value;
    this.ngxSpinner.show();

    this.cvService.updateCVBasic({...data, username: "bicosind@gmail.com"}).subscribe(
      (response: any) => {
       console.log(response);
        
       this.getCurrentUser();
        this.visibleInfoPersonal = false;
        this.isLoadingGeneral=false;
        this.ngxSpinner.hide();
      },
      (errorResponse: HttpErrorResponse) => {
        this.message.create("error", 'Ha ocurrido un error al actualizar la información');
        this.isLoadingGeneral=false;
        this.ngxSpinner.hide();
      }
    )


  
  }

  //FUNCIONES DE EXPERIENCIA DE USUARIO -----------------------------------------------

  saveExperienceUser(): void {
    
    if (!this.registerRecruiterExperienceProfessionalForm.valid) {
      Object.values(this.registerRecruiterExperienceProfessionalForm.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
      return;
    } 


    let form = this.registerRecruiterExperienceProfessionalForm.value;    

    this.cvService.addWorkExperience({...form, userId: this.userId}).subscribe(
      (response: any) => {  
        console.log(response);
        this.getWorkExperiencesByUser();
        this.isLoadingWorkExperience = false;     
        this.visibleAddExperience = false;  
        this.registerRecruiterExperienceProfessionalForm.reset()
      },
      (errorResponse: HttpErrorResponse) => {
        this.message.create("error", 'Ha ocurrido un error al recuperar las experiencias de trabajo');
        this.isLoadingWorkExperience = false;
      }
    )

  }

  editExperienceUser(): void {

    if (!this.registerRecruiterExperienceProfessionalForm.valid) {
      Object.values(this.registerRecruiterExperienceProfessionalForm.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
      return;
    }

    this.isLoadingWorkExperience = true;


    let form = this.registerRecruiterExperienceProfessionalForm.value;

    this.cvService.updateExperienceByUser(this.workExperienceEdit.id,form).subscribe(
      (response: any) => {  
        console.log(response);
        this.getWorkExperiencesByUser();
        this.isLoadingWorkExperience = false;     
        this.visibleEditExperience = false;  
      },
      (errorResponse: HttpErrorResponse) => {
        this.message.create("error", 'Ha ocurrido un error al recuperar las experiencias de trabajo');
        this.isLoadingWorkExperience = false;
      }
    )
    



    // this.experiencesWorkUser[this.idExperienceProfessional] = this.registerRecruiterExperienceProfessionalForm.value;
    // this.visibleAddExperiences = false;
    // this.registerRecruiterExperienceProfessionalForm.reset()

  }

  showModalEditExp(element : any): void {
    this.getWorkExperienceById(element.id);
    this.visibleEditExperience = true;
  }

  closeModalEditExp() {
    this.visibleEditExperience = false;
  }


  showModalAddExp() {
    this.workExperienceEdit = undefined;
    this.registerRecruiterExperienceProfessionalForm.reset()
     this.visibleAddExperience = true;
  }

  closeModalAddExp() {
    this.visibleAddExperience = false;
  }

  deleteExperienceUser(): void {
    this.modalService.confirm({
      nzTitle: `Eliminar experiencia "${this.workExperienceEdit.job}"`,
      nzContent: '¿Seguro que deseas eliminar la siguiente experiencia de trabajo?',
      nzOkText: 'Eliminar',
      nzCancelText: 'Cerrar',
      nzOnOk: () =>{

        this.isLoadingWorkExperience = true;     
        
        this.cvService.deleteExperiencesById(this.workExperienceEdit.id).subscribe(
          (response: any) => {  
            console.log(response);
            this.getWorkExperiencesByUser();
            this.listWorkExperiences = response;
            this.isLoadingWorkExperience = false;     
            this.visibleEditExperience = false;  
          },
          (errorResponse: HttpErrorResponse) => {
            this.message.create("error", 'Ha ocurrido un error al recuperar las experiencias de trabajo');
            this.isLoadingWorkExperience = false;
          }
        )

      }
    });

  }

  //FUNCIONES DE HABILIDADES USUARIO -------------------------------------------------------
  showModalSkills(): void {
    this.getSkillsByUserEdit();    
  }

  closeModalSkills() {
    this.visibleAddSkills = false;
  }

  saveSkillsUser(): void {
    if (!this.skillsForm.valid) {
      this.visibleAddSkills = false;
      Object.values(this.skillsForm.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
      return;
    } 

    let form = this.skillsForm.value;
    this.isLoadingSkillsSave = true;
    this.ngxSpinner.show();

      
    this.cvService.addSkills({userId: this.userId, skills: form.skills}).subscribe(
      (response: any) => {  
        console.log(response);
        this.getskillsByUser();
        this.isLoadingSkillsSave = false;     
        this.visibleAddSkills = false;  
        this.ngxSpinner.hide();

      },
      (errorResponse: HttpErrorResponse) => {
        this.message.create("error", 'Ha ocurrido un error al recuperar las experiencias de trabajo');
        this.isLoadingSkillsSave = false;
        this.ngxSpinner.hide();

      }
    )
  }

  getSkillsByUserEdit() {
    this.isLoadingGeneral = true;
    this.cvService.getSkillsByUser(this.userId).subscribe(
      (response: any) => {
       response.forEach((prop: any, key: any) => {
          this.listSkillsEdit.push(prop.value)   
        });         
        this.isLoadingGeneral = false;  
        this.visibleAddSkills = true;

        console.log(this.listSkillsEdit);
        

      },
      (errorResponse: HttpErrorResponse) => {
        this.message.create("error", 'Ha ocurrido un error al recuperar las skills');
        this.isLoadingGeneral = false;
      }
    )
  }

  //FUNCIONES DE EDUCACION USUARIO -------------------------------------------------------

  showModalEducation(index: any = 'NO_EDIT'): void {
   
  }

  deleteEducationUser(): void {

   

  }

  saveEducationUser(): void {
    if (this.EducationUserForm.valid) {
      this.educationUser.push(this.EducationUserForm.value);
      this.visibleAddEducation = false;
      this.EducationUserForm.reset()
    } else {
      Object.values(this.EducationUserForm.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
    }
  }

  EditEducationUser(): void {
    if (this.EducationUserForm.valid) {
      this.educationUser[this.idEducationUser] = this.EducationUserForm.value;
      this.visibleAddEducation = false;
      this.EducationUserForm.reset()
    } else {
      Object.values(this.EducationUserForm.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
    }
  }

  //FUNCIONES DE CERTIFICADOS USUARIO -------------------------------------------------------

  showModalCertificate(index: any = 'NO_EDIT'): void {
    
  }

  deleteCertificateUser(): void {

  }

  saveCertificateUser(): void {
    if (this.CertificateUserForm.valid) {
      this.certificateUserArray.push(this.CertificateUserForm.value);
      this.visibleAddCertificate = false;
      this.CertificateUserForm.reset()
    } else {
      Object.values(this.CertificateUserForm.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
    }
  }

  EditCertificateUser(): void {
    if (this.CertificateUserForm.valid) {
      this.certificateUserArray[this.idCertificateUser] = this.CertificateUserForm.value;
      this.visibleAddCertificate = false;
      this.CertificateUserForm.reset()
    } else {
      Object.values(this.CertificateUserForm.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
    }
  }


  //VALIDACIÓN DE INPUT PARA NO ACEPTAR LETRAS
  public validateFormat(event: any) {
    let key;
    if (event.type === 'paste') {
      key = event.clipboardData.getData('text/plain');
    } else {
      key = event.keyCode;
      key = String.fromCharCode(key);
    }
    const regex = /[0-9]|\./;
    if (!regex.test(key)) {
      event.returnValue = false;
      if (event.preventDefault) {
        event.preventDefault();
      }
    }
  }


  public technicalAttitudes: any = [
    {
      id: 1,
      value: 'Manejo de hojas de cálculo'
    },
    {
      id: 2,
      value: 'Uso de programas de edición fotográfica'
    },
    {
      id: 3,
      value: 'Redacción de textos'
    },
    {
      id: 4,
      value: 'Java'
    },
    {
      id: 5,
      value: 'Programación orientada objetos'
    },
    {
      id: 6,
      value: 'C#'
    },
    {
      id: 7,
      value: 'C++'
    },
    {
      id: 8,
      value: 'AWS Services'
    },
    {
      id: 9,
      value: 'Azure Devops'
    },
    {
      id: 10,
      value: 'Google Cloud'
    },
    {
      id: 11,
      value: 'C'
    },
    {
      id: 12,
      value: 'Ruby and Rails'
    },
    {
      id: 13,
      value: 'Python'
    },
    {
      id: 14,
      value: '.Net Core'
    },
    {
      id: 15,
      value: 'Angular'
    },
    {
      id: 16,
      value: 'React.js'
    },
    {
      id: 17,
      value: 'Next.js'
    },
    {
      id: 18,
      value: 'MySQL'
    },
    {
      id: 19,
      value: 'SQL Server'
    },
    {
      id: 20,
      value: 'RxJS'
    },
    {
      id: 21,
      value: 'Linux'
    },
    {
      id: 23,
      value: 'PLSQL-Oracle'
    },
    {
      id: 24,
      value: 'SQLite'
    },
    {
      id: 25,
      value: 'Git'
    },
    {
      id: 26,
      value: 'Spring Boot'
    },
    {
      id: 27,
      value: 'Spring Framework'
    },
    {
      id: 28,
      value: 'Servicios Rest'
    },
    {
      id: 29,
      value: 'Microservicios'
    },
    {
      id: 30,
      value: 'Servicios SOAP'
    },
  ];







}
