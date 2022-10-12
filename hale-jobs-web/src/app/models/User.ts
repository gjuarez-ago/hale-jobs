export class User {
        
    id : string;
    username: string;
    gender: string;
    dateOfBirth: any;
    names: string;
    surnames: string;
    motherLastName: string;
    fatherLastName: string;
    numberPhone?: any;
    role: string;
    token: string;
    email : string;
    expireToken?: number;
    profileImageUrl: string;
    joinDate: any;
    authorities: string[];
    location?: any;
    lastLoginDate: any;
    lastLoginDateDisplay: any;
    isActive: boolean;
    isNonLocked: boolean;
    notLocked : boolean;
    active : boolean;

constructor() {
  this.id = "";
  this.names = '';
  this.surnames = '';
  this.username = '';
  this.numberPhone = 0;
  this.token = '';
  this.email = ''
  this.motherLastName = '';
  this.fatherLastName = '';
  this.expireToken = 0;
  this.gender = '';
  this.location = '';
  this.dateOfBirth = new Date();
  this.lastLoginDate = null;
  this.lastLoginDateDisplay = null;
  this.joinDate = null;
  this.profileImageUrl = '';
  this.isActive = false;
  this.isNonLocked = false;
  this.role = '';
  this.notLocked = false;
  this.active = false;
  this.authorities = [];
  
}

}
