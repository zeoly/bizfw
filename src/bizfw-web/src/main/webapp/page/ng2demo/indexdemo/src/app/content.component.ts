import { Component } from '@angular/core';

@Component({
    selector: 'my-content',
    templateUrl: './content.component.html'
})
export class ContentComponent {
    seleted: string;
    items: string[] = [
        'AAA', 'BBB'
    ];
}