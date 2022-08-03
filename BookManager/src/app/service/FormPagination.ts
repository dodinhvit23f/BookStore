import { Editor, Toolbar } from 'ngx-editor';

export abstract class FormPagination{

  public editor!: Editor;

  tab = "tab";
  btnBack = "btnBack";
  btnNext = "btnNext";
  btnSubmit = "btnSubmit";

  durationInSeconds = 2

  numberOfTab = 0;
  currentTab = 0;
  previousTab = -1;

  toolbar: Toolbar = [
    ['bold', 'italic'],
    ['underline', 'strike'],
    ['ordered_list', 'bullet_list'],
    [{ heading: ['h1', 'h2', 'h3', 'h4', 'h5', 'h6'] }],
    ['text_color', 'background_color'],
    ['align_left', 'align_center', 'align_right', 'align_justify'],
  ];

  next(): void {
    this.previousTab = this.currentTab.valueOf();
    this.currentTab = this.currentTab + 1;
    this.showTab();
  }


  back(): void {
    this.previousTab = this.currentTab.valueOf();
    this.currentTab = this.currentTab - 1;
    this.showTab();
  }


  loadButtons() {

    var buttonSubmit = document.getElementById(this.btnSubmit) as HTMLElement;
    var buttonBack = document.getElementById(this.btnBack) as HTMLElement;
    var buttonNext = document.getElementById(this.btnNext) as HTMLElement;


    if (this.currentTab === this.numberOfTab) {
      buttonNext!.style.display = "none";
      buttonSubmit!.style.display = "inline";
      return;
    }

    if (this.currentTab === 0) {
      buttonSubmit!.style.display = "none";
      buttonBack!.style.display = "none";
      return;
    }

    buttonNext!.style.display = "inline";
    buttonBack!.style.display = "inline";
    buttonSubmit!.style.display = "none";
  }

  markTab() {
    var steps = Array.from(document.getElementsByClassName("step") as HTMLCollectionOf<HTMLElement>);
    let addedClass = "finish"

    for (var i = 0; i <= this.numberOfTab; i++) {
      if (i <= this.currentTab) {
        steps[i].classList.add(addedClass);
        continue
      }

      if (steps[i].classList.contains(addedClass)) {
        steps[i].classList.remove(addedClass)
      }

    }
  }

  showTab() {
    var tabs = Array.from(document.getElementsByClassName(this.tab) as HTMLCollectionOf<HTMLElement>);
    this.numberOfTab = document.getElementsByClassName(this.tab).length - 1

    tabs[this.currentTab].style.display = "block";

    if (this.previousTab != -1) {
      tabs[this.previousTab].style.display = "none";
    }

    this.loadButtons();
    this.markTab();
  }

  abstract submit(): void
}

