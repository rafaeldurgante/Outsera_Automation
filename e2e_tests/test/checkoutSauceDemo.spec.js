const { test, expect } = require('@playwright/test');

test('Processo de checkout com 3 itens específicos e validação dos preços', async ({ page }) => {
    // Navega para a página de login
    await page.goto('https://www.saucedemo.com/');
    await page.fill('input[name="user-name"]', 'standard_user');
    await page.fill('input[name="password"]', 'secret_sauce');
    await page.click('input[type="submit"]');

    // Adiciona os três itens ao carrinho
    await page.click('#add-to-cart-sauce-labs-backpack');
    await page.click('#add-to-cart-sauce-labs-bike-light');
    await page.click('#add-to-cart-sauce-labs-bolt-t-shirt');

    // Navega para o carrinho
    await page.click('.shopping_cart_link');

    // Valida que os três itens estão no carrinho
    await expect(page.locator('.cart_item:has-text("Sauce Labs Backpack")')).toBeVisible();
    await expect(page.locator('.cart_item:has-text("Sauce Labs Bike Light")')).toBeVisible();
    await expect(page.locator('.cart_item:has-text("Sauce Labs Bolt T-Shirt")')).toBeVisible();

    // Inicia o processo de checkout
    await page.click('text=Checkout');

    // Preenche os dados de checkout
    await page.fill('input[name="firstName"]', 'Rafael');
    await page.fill('input[name="lastName"]', 'Durgante');
    await page.fill('input[name="postalCode"]', '12345');
    await page.click('input[type="submit"]');

    // Valida os preços dos itens no checkout
    const backpackPrice = page.locator('.inventory_item_price:has-text("$29.99")');
    const bikeLightPrice = page.locator('.inventory_item_price:has-text("$9.99")');
    const boltTShirtPrice = page.locator('.inventory_item_price:has-text("$15.99")');

    await expect(backpackPrice).toBeVisible();
    await expect(bikeLightPrice).toBeVisible();
    await expect(boltTShirtPrice).toBeVisible();

    // Valida o total dos itens, taxa e total final
    const itemTotal = await page.locator('.summary_subtotal_label');
    const tax = await page.locator('.summary_tax_label');
    const total = await page.locator('.summary_total_label');

    await expect(itemTotal).toHaveText('Item total: $55.97');
    await expect(tax).toHaveText('Tax: $4.48');
    await expect(total).toHaveText('Total: $60.45');

    // Confirma o pedido
    await page.click('text=Finish');

    // Verifica se a mensagem de sucesso aparece
    const successMessage = await page.locator('.complete-header');
    await expect(successMessage).toHaveText('Thank you for your order!');
});
