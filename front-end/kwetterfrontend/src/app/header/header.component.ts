import { Component, OnInit } from '@angular/core';
import {ProfileService} from "../profile.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private profileservice:ProfileService) { }

  ngOnInit() {
  }

}
