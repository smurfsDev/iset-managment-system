<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class club extends Model
{
    use HasFactory;
    protected $table = 'clubs';
    protected $fillable = [
        "nom",
        "dateCreation",
        "responsableClub",
        "president",
        "vicePresident"
    ];

    public function demandeAdhesionClub(){
        return $this->HasMany('App\Models\DemandeAdhesionClub', 'club_id');
    }
}
