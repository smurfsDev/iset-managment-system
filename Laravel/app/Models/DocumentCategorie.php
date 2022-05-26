<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class DocumentCategorie extends Model
{
    use HasFactory;
    protected $fillable = [
        "title",
        "idresponsable",
    ];
    // relation this Category has many Materiels
    public function documents(){
        return $this->hasMany(Document::class,'categorie_document_id');
    }
}
