export class MinimalUser {
    name: Name;
}


export class UserInfo {

  name: Name;
  password: string;
  role: string;
  email: string;
  credit: number;

  constructor() {
    this.name = new Name();
    this.role = "NATURAL_PERSON"
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
