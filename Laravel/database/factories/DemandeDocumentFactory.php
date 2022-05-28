<?php

namespace Database\Factories;

use App\Models\DocumentCategorie;
use App\Models\User;
use Database\Seeders\DocumentCategorieSeeder;
use Illuminate\Database\Eloquent\Factories\Factory;

class DemandeDocumentFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition()
    {
        return [
            "titre"=>$this->faker->word(),
            "description"=>$this->faker->sentence(),
            "document_categories_id"=> DocumentCategorie::get('id')->random(),
            "file"=>$this->faker->word(),
            "reponse"=>$this->faker->sentence(),
            'idResponsable' =>User::get('id')->random(), 
        ];
    }
}
