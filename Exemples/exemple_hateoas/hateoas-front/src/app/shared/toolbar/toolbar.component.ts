import { Component, OnInit } from '@angular/core';
import { UtilService } from 'src/app/service/util.service';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent implements OnInit {

  constructor(private readonly util: UtilService,) { }

  ngOnInit(): void {
  }

  backHome(): void {
    this.util.backHome();
  }

}
