import {Component, Input, OnInit} from '@angular/core';
import {ProfileService} from "../profile.service";

@Component({
  selector: 'app-timeline',
  templateUrl: './timeline.component.html',
  styleUrls: ['./timeline.component.css']
})
export class TimelineComponent implements OnInit {
  public timeline: any = {};
  private hasTimeline: boolean;
  @Input() username;

  constructor(protected profileservice: ProfileService) { }


  ngOnInit() {
    this.getOwnTimeline();
  }
  ngOnChanges(){
    this.getOwnTimeline();
  }

  public getOwnTimeline() {
    this.profileservice.getOwnTimeline(this.username).subscribe((res: any) => {
        console.log("getowntimeline", res);
        if (res[0] != null) {
          this.timeline = res.map(kweet => {
            return {...kweet, postDate: new Date(kweet.postDate).toLocaleDateString()};
          });
          this.hasTimeline = true;
        }
        else {
          this.timeline = null;
          this.hasTimeline = false;
        }
      },
      err => console.log(err),
      () => console.log("Done loading all the kweets of " + this.username)
    );
  }

}
