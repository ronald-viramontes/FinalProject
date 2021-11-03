import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-search',
  templateUrl: './user-search.component.html',
  styleUrls: ['./user-search.component.css']
})
export class UserSearchComponent implements OnInit {

  keyword: string = '';
  searchResults: User[] = [];
  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  search(skillKeyword: string){
    this.userService.indexBySkill(skillKeyword).subscribe(
      data => {
        this.searchResults = data;
      },
      err => {
        console.error(err);
      }
    );
  }
}
