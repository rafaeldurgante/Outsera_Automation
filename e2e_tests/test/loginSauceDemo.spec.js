const { test, expect } = require('@playwright/test');

test('Login do usuário e validação da página de inventário', async ({ page }) => {
    await page.goto('https://www.saucedemo.com/');
    await page.fill('input[name="user-name"]', 'standard_user');
    await page.fill('input[name="password"]', 'secret_sauce');
    await page.click('input[type="submit"]');

    // Verifica se a URL contém 'inventory.html'
    await expect(page).toHaveURL(/.*inventory.html/);
});
