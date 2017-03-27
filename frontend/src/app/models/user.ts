export class MinimalUser {
    name: Name;
}


export class UserInfo {

  name: Name;
  password: string;
  role: UserRole;
  email: string;
  credit: number;

  constructor() {
    this.name = new Name();
    this.role = UserRole.NATURAL_PERSON;
  }
}

export class AuthenticatedUser {
  token: string;
  authenticated: boolean;
  userInfo: UserInfo;

  constructor() {
  }
}

export class Name {
  firstName: string;
  lastName: string;

  constructor() {  }
}

export class UserCredentials {
  email: string;
  password: string;

  constructor() {}

}

export enum UserRole {
  NATURAL_PERSON, LEGAL_PERSON
}