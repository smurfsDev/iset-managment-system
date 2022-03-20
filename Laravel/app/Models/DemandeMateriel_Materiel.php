<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class DemandeMateriel_Materiel extends Model
{
    use HasFactory;

    protected $table='DemandeMateriel_Materiel';
    protected $fillable = [
        "idDemande",
        "idMateriel",
        "quantité",
    ];
}
