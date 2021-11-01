export class User {
  id: number;
  username: string;
  password: string;
  enabled: boolean;
  role: string;
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  imageUrl: string;

  constructor(
    id: number = 0,
    username: string = '',
    password: string = '',
    enabled: boolean = true,
    role: string = '',
    firstName: string = '',
    lastName: string = '',
    email: string = '',
    phoneNumber: string = '',
    imageUrl: string = ''
  ) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.enabled = enabled;
    this.role = role;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.imageUrl = imageUrl;
  }
}
