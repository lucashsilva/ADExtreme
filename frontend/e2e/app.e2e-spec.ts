import { AdextremePage } from './app.po';

describe('adextreme App', function() {
  let page: AdextremePage;

  beforeEach(() => {
    page = new AdextremePage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
