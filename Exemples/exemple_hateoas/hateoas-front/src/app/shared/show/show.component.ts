import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-show',
  templateUrl: './show.component.html',
  styleUrls: ['./show.component.css']
})
export class ShowComponent implements OnInit {

  

  constructor() { }

  @Input() show: any;
  @Input() withSeletion= false;
  @Input() withPoster= false;
  @Output() selected = new EventEmitter();

  ngOnInit(): void {
  }

  select(show: any) {
    this.selected.emit(show);
  }
}
